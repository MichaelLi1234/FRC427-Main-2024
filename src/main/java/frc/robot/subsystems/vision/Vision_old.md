# [Vision_old Subsystem](https://github.com/RoboLancers/FRC427-Main-2024/blob/main/src/main/java/frc/robot/subsystems/vision/Vision_old.java)

---

## Description

This subsystem controls a Limelight camera for the 427 robot. It uses the camera and its network table to record its position and rotation whilst retrieving it. It also determines how far away each respective April Tags is.

## Connected Devices
- Camera ( `limelightNT` ) for recording distance and identifying April Tags. Also acts as a way to store information to a network table and then to the SmartDashboard.

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
Recieves the latest results of the robot and cameras position from the SmartDashboard and stores it. Checks if the robot is on or off, reads the values of its position, and converts its various positional values into variables.

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
| `LimelightTotalLatency` | The total latency in ms, how much much delay there is, of the Limelight |
| `LimelightTargetArea` | 3D transform of the camera in the coordinate system of the primary in-view AprilTag |
| `LimelightTargetX` | The X transform of the camera in the coordinate system of the primary in-view AprilTag |
| `LimelightTargetY` | The Y transform of the camera in the coordinate system of the primary in-view AprilTag |
| `LimelightNearestAprilTag` | Which AprilTag is closest to the Limelight | Uses the `getClosestAprilTagID` method |
| `Limelight Connected` | Whether the Limelight is connected or not |

### 