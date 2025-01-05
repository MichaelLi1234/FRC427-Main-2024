# [FrontVision Subsystem](https://github.com/RoboLancers/FRC427-Main-2024/blob/main/src/main/java/frc/robot/subsystems/vision/FrontVision.java)

---

## Description

The FrontVision subsystem controls a Limelight camera for the 427 robot. It record the gamepiece's (Notes) position and rotation and determines how far away it is to the robots intake.

## Connected Devices

- Camera ( `PhotonCamera` ) for gauging the notes distance and what its position / orientation is.

## Picture

![alt text](https://cdn.andymark.com/product_images/limelight-3/63ceb4499a4720101863db46/zoom.jpg?c=1674490953 "Limelight")

## Methods

|Method name |Returns     |Parameters  |Description |
|:----------:|:----------:|:----------:|:----------:|
| `getInstance` | instance of `FrontVision` | N/A | creates a new version of the FrontVision |
| `getLatestVisionResult` | instance of `PhotonPipelineResult` | N/A | Relays the latest results of the vision from the Photon Camera |
| `getLastSuccessfulResult` | instance of `PhotonPipelineResult` | N/A | Relays the last successful of the Photon Camera |
| `getNoteYaw` | `double` | N/A | Gives the yaw needed to get the note |
| `getNotePitch` | `double` | N/A | Gives the pitch needed to get the note |
| `getCurrentNoteForwardDistance` | `double` | N/A | Gives the forward distance needed to reach the note |
| `getCurrentNoteHorizontalDistance` | `double` | N/A | Gives the horizontal distance needed to reach the note |
| `getNotePose` | instance of `Transform2d` | N/A | Gives the position of the note |
| `getNoteForwardDistance` | `double` | `pitch` | Calculates the forward distance needed to reach the note |
| `getNoteHorizontalDistance` | `double` | `pitch` , `yaw` | Calculates the horizontal distance needed to reach the note |

## Periodic

### Logging
Logs the following values periodically onto the SmartDashboard:
| Name | Value | Notes |
|:----:|:-----:|:-----:|
| `Front Camera is Connected` | `Whether the camera is connected or not` |  |
| `Target Yaw` | `The yaw of the note` | used with `getNoteYaw` |
