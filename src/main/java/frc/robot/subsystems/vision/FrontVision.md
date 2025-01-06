# [FrontVision Subsystem](https://github.com/RoboLancers/FRC427-Main-2024/blob/main/src/main/java/frc/robot/subsystems/vision/FrontVision.java)

---

## Description

The FrontVision subsystem controls a PhotonVision camera for the 427 robot. It record the gamepiece's (Notes) position and rotation and determines how far away it is to the robots intake.

## Connected Devices

- Camera ( `PhotonCamera` ) for gauging the notes distance and what its position / orientation is.

## Picture

![alt text](https://raw.githubusercontent.com/RoboLancers/RoboLancers-Hardware/refs/heads/main/Helios/Images/CitrusSight_Black_2-3Ratio_Fit_3_20_Render.jpg "Limelight")

## PhotonPipelineResult

[Link to Properties and Methods](https://javadocs.photonvision.org/org/photonvision/vision/pipeline/result/CVPipelineResult.html)

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
| `` | `` |

## Periodic
Recieves the latest results from the SmartDashboard and stores it
### Logging
Logs the following values periodically onto the SmartDashboard:
| Name | Value | Notes |
|:----:|:-----:|:-----:|
| `Front Camera is Connected` | `Whether the camera is connected or not` |  |
| `Target Yaw` | `The yaw of the note` | used with `getNoteYaw` |
