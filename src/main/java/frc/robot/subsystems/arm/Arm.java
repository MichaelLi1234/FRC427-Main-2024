package frc.robot.subsystems.arm;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkBase.SoftLimitDirection;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.SparkAbsoluteEncoder.Type;
import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
 

public class Arm extends SubsystemBase {
    // Define and initiate variables for arm
    private double m_targetPosition = Constants.ArmConstants.kTravelPosition;
    private double m_manualVelocity = 0;

    private DigitalInput m_limitSwitch = new DigitalInput(Constants.ArmConstants.kLimitSwitchId);

    private CANSparkMax m_armMotorRight = new CANSparkMax(Constants.ArmConstants.kArmMotorRightId, MotorType.kBrushless);
    private CANSparkMax m_armMotorLeft = new CANSparkMax(Constants.ArmConstants.kArmMotorLeftId, MotorType.kBrushless);

    private AbsoluteEncoder m_armEncoderRight = m_armMotorRight.getAbsoluteEncoder(Type.kDutyCycle);
    
    private PIDController m_armPIDController = new PIDController(Constants.ArmConstants.kP, Constants.ArmConstants.kI, Constants.ArmConstants.kD);
    
    public ArmFeedforward m_armFeedforward = new ArmFeedforward(Constants.ArmConstants.kS, Constants.ArmConstants.kG, Constants.ArmConstants.kV, Constants.ArmConstants.kA);

    private ArmControlType m_ArmControlType = Arm.ArmControlType.PID;

    public Arm() {
        setupMotors();
    }

    // motor and encoder config
    public void setupMotors() {
        m_armMotorRight.setInverted(Constants.ArmConstants.kRightMotorInverted);
        m_armMotorLeft.setInverted(Constants.ArmConstants.kLeftMotorInverted);
        
        m_armMotorRight.setSmartCurrentLimit(Constants.ArmConstants.kMotorCurrentLimit);
        m_armMotorLeft.setSmartCurrentLimit(Constants.ArmConstants.kMotorCurrentLimit);

        // forward soft limit
        m_armMotorRight.enableSoftLimit(SoftLimitDirection.kForward, true);
        m_armMotorLeft.enableSoftLimit(SoftLimitDirection.kForward, true);
        m_armMotorRight.setSoftLimit(SoftLimitDirection.kForward, Constants.ArmConstants.kSoftLimitForward);
        m_armMotorLeft.setSoftLimit(SoftLimitDirection.kForward, Constants.ArmConstants.kSoftLimitForward);
        
        // reverse soft limit
        m_armMotorRight.enableSoftLimit(SoftLimitDirection.kReverse, true);
        m_armMotorLeft.enableSoftLimit(SoftLimitDirection.kReverse, true);        
        m_armMotorRight.setSoftLimit(SoftLimitDirection.kReverse, Constants.ArmConstants.kSoftLimitReverse);
        m_armMotorLeft.setSoftLimit(SoftLimitDirection.kReverse, Constants.ArmConstants.kSoftLimitReverse);

        // conversion factors
        m_armEncoderRight.setPositionConversionFactor(Constants.ArmConstants.kPositionConversionFactor);
        m_armEncoderRight.setVelocityConversionFactor(Constants.ArmConstants.kVelocityConversionFactor);
        
        // position error on which it is tolerable
        m_armPIDController.setTolerance(Constants.ArmConstants.kTolerance);
        
        // left arm motor would follow right arm  motor's voltage intake 
        m_armMotorLeft.follow(m_armMotorRight);
    }

    public void periodic() {
        doSendables();
        
        double impendingVelocity = 0; 

        // velocity controlled by PID and FF
        if (m_ArmControlType == ArmControlType.PID) {
           impendingVelocity = m_armPIDController.calculate(m_armEncoderRight.getPosition(), m_targetPosition) + m_armFeedforward.calculate(m_armEncoderRight.getPosition(), 0);
        }
        // velocity controlled manually
        else if (m_ArmControlType == ArmControlType.MANUAL) {
            impendingVelocity = m_manualVelocity;
        }

        // if the limit switch is not on and the robot arm is moving forward,
        // arm is allowed to move. 
        // forward defined as away from the limit switch.
        if (!m_limitSwitch.get() || impendingVelocity >= 0) {
                m_armMotorRight.set(impendingVelocity); 
        }

    }

    public boolean goToAngle(double angle) {
        this.m_targetPosition = angle;
    }

    public double getAngle() {
        return m_armEncoderRight.getPosition();
    }

    public void setSpeed(double speed) {
        this.m_manualVelocity = speed;
    }

    public boolean isAtAngle() {
        return m_armPIDController.atSetpoint();
        
    }

    public void setControlType(ArmControlType type) {
        this.m_ArmControlType = type;
    }

    public void setPID(double p, double i, double d) {
        this.m_armPIDController.setPID(p, i, d);
    }

    public enum ArmControlType {
        MANUAL, 
        PID
    }

    // add logging for arm 
    public void doSendables() {
        SmartDashboard.putNumber("Arm Position (degrees)", getAngle()); 
        SmartDashboard.putNumber("Arm Velocity (degrees/sec)", m_armEncoderRight.getVelocity());
        SmartDashboard.putBoolean("Is Arm At Set Point", m_limitSwitch.get());
        SmartDashboard.putBoolean("Arm Limit Switch", isAtAngle());
        SmartDashboard.putString("Arm Control Type", m_ArmControlType.toString());
    }
}
