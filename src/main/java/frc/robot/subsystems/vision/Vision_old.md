# [Vision_old Subsystem](https://github.com/RoboLancers/FRC427-Main-2024/blob/main/src/main/java/frc/robot/subsystems/vision/Vision_old.java)

---

## Description

This subsystem controls a Limelight camera for the 427 robot. It uses the camera to record its position and determines how far away each respective April Tags is.

## Connected Devices
- Camera ( `limelightNT` ) for recording distance and identifying April Tags

## Picture

![alt text](https://cdn.andymark.com/product_images/limelight-3/63ceb4499a4720101863db46/zoom.jpg?c=1674490953 "Limelight")

## Methods

|Method name |Returns     |Parameters  |Description |
|:----------:|:----------:|:----------:|:----------:|
| `getinstance` | instance of `Vision_old` | N/A | creates a new version of the Vision_old |
| `calculateVisionStdDevs` | instance of `Matrix` | N/A | Calculates the standard deviation of the distance of the object |
| `getClosestAprilTagID` | `int` | N/A | Gets the ID of the nearest AprilTag |
| `getAprilTagPos` | instance of `Pose3d` | `aprilTagID` | Returns the Pose3d of the closest april tag |
| `getDistanceToAprilTag` | `double` | N/A | Gets the distance to the closest april tag by finding the 3-dimensional norm |
| `getDistanceBetweenPose3d` | `double` | `Pose3d firstpose` , `Pose3d secondpose` | Returns the distance between two pose3d objects in three dimensions |
| `getDistanceBetweenPose2d` | `double` | `Pose2d firstPose` , `Pose2d secondPose` | Returns the distance between two pose2d objects in two dimensions |
| `getRotation3d` | instance of `Rotation3d` | N/A | Returns the rotation3d using values stored in the network table |
| `getCurrentPose3d` | instance of `Pose3d` | N/A |  Returns the rotation3d using values stored in the network table |
| `addVisionFromDrivetrain` | `void` | N/A | Adds vision measurements to the drivetrain if they are within the field |
| `isPoseValid` | `boolean` | N/A | Sees if the robot's position as given by the limelight is within the field |


## Periodic

### Logging
Logs the following values onto the SmartDashboard:
| Name | Value | Notes |
|:----:|:-----:|:-----:|
| `LimelightX` | The X position of the Limelight | Used in the `getCurrentPose3d` method |
| `LimelightY` | The Y position of the Limelight | Used in the `getCurrentPose3d` method |
| `LimelightZ` | The Z position of the Limelight | Used in the `getCurrentPose3d` method |
| `LimelightRoll` | The roll, front to back, rotation of the Limelight | Used in the `getRotation3d` method |
| `LimelightPitch` | The pitch, side to side, rotation of the Limelight | Used in the `getRotation3d` method |
| `LimelightYaw` | The yaw, around the vertical axis, rotation of the Limelight | Used in the `getRotation3d` method |
| `LimelightTotalLatency` | The total latency, how much much delay there is, of the Limelight |
| `LimelightTargetArea` | The area the Limelight is targetting |
| `LimelightTargetX` | The X position of the area the Limelight is targetting |
| `LimelightTargetY` | The Y position of the area the Limelight is targetting |
| `LimelightNearestAprilTag` | Which AprilTag is closest to the Limelight | Uses the `getClosestAprilTagID` method |
| `Limelight Connected` | Whether the Limelight is connected or not |