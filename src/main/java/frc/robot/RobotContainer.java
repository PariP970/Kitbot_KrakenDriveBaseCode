// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.



package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;


// ===== Input Devices ===== //
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.commands.EmergencyStopMechanismsCmd;


// ===== Swerve Specific ===== //
import frc.robot.commands.SwerveJoystickCmd;
import frc.robot.subsystems.SwerveSubsystem;


// ===== Constants ===== //
import frc.robot.Constants.OIConstants;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.ShooterSubsytem; // IMPORTANT: Import your IntakeSubsystem

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */




public class RobotContainer {

    // Subsystems
    private final SwerveSubsystem swerveSubsystem;

    // Control Inputs
    //private final Joystick controller = new Joystick(OIConstants.kOperatorControllerPort);
    private final Joystick controller = new Joystick(OIConstants.kOperatorControllerPort);
    private final Joystick translateJoystick = new Joystick(OIConstants.kDriverTranslateStickPort);
    private final Joystick rotateJoystick = new Joystick(OIConstants.kDriverRotateStickPort);

    private final ShooterSubsytem shooterSubsystem = new ShooterSubsytem();


    public RobotContainer() {

        swerveSubsystem = new SwerveSubsystem();


        swerveSubsystem.setDefaultCommand(new SwerveJoystickCmd(
            swerveSubsystem,
            () -> -controller.getRawAxis(OIConstants.kDriverYAxis),
            () -> -controller.getRawAxis(OIConstants.kDriverXAxis),
            () -> -controller.getRawAxis(OIConstants.kDriverRotAxis),
            () -> controller.getRawButton(OIConstants.kDriverBoostButtonId), 
            () -> controller.getRawButton(OIConstants.kController_leftBumper)));


        configureBindings();
    }

    

    private void configureBindings() {

        new JoystickButton(controller, OIConstants.kDriverResetGyroButtonId).onTrue(swerveSubsystem.zeroHeading());
        new JoystickButton(controller, 3).onTrue(shooterSubsystem.out()).onFalse(shooterSubsystem.stopCommand());
        new JoystickButton(controller, 4).onTrue(shooterSubsystem.in()).onFalse(shooterSubsystem.stopCommand());
    

    
        //new JoystickButton(controller, OIConstants.kDriverStopButtonId).onTrue(new EmergencyStopMechanismsCmd());
    }



    public Command getAutonomousCommand() {
        return Commands.print("No autonomous command configured");
    }
    
}

