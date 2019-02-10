// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.frc2881.commands.scoring.hp;

import edu.wpi.first.wpilibj.command.Command;
import org.frc2881.Robot;
import org.frc2881.commands.basic.rumble.RumbleNo;
import org.frc2881.controllers.PS4;
import org.frc2881.subsystems.Intake.RollerState;
import org.frc2881.utils.AmpMonitor;

/**
 * 
 *
 */
public class HPControlRollers extends Command {

    private AmpMonitor ampMonitor = new AmpMonitor(10, () -> Robot.intake.getHPRollerCurrent());
    private boolean monitoringAmps;
    private double speedCap;
    private double joystickValue;
    private double programTime;

    public HPControlRollers() {
        requires(Robot.intake);
}

    @Override
    protected void initialize() {
        Robot.logInitialize(this);
        monitoringAmps = false;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        if (!ampMonitor.isTriggered() && (programTime == 0 || timeSinceInitialized() > 500 ||
                joystickValue + Robot.oi.manipulator.getRawAxis(PS4.LEFT_TRIGGER) >= 0.25)) {
            Robot.intake.HPRollers(Robot.oi.manipulator.getRawAxis(PS4.LEFT_TRIGGER), RollerState.INTAKE);
        }

        else if (!ampMonitor.isTriggered() && (programTime == 0 || timeSinceInitialized() > 500 ||
            joystickValue - Robot.oi.manipulator.getRawAxis(PS4.RIGHT_TRIGGER) >= 0.25)) {
        Robot.intake.HPRollers(Robot.oi.manipulator.getRawAxis(PS4.RIGHT_TRIGGER), RollerState.EJECT);
         }

        else {
            Robot.intake.HPRollers(speedCap, RollerState.EJECT);
        }

        if (!monitoringAmps && timeSinceInitialized() > .2){
            ampMonitor.reset();
            monitoringAmps = true;
        }

        if (monitoringAmps && ampMonitor.isTriggered()) {
            Robot.log("HP Roller current limit exceeded");

            speedCap = 0.2;

            //Makes joystick value the trigger that is being pressed
            if (Robot.oi.manipulator.getRawAxis(PS4.RIGHT_TRIGGER) <= 0.15 &&
                 Robot.oi.manipulator.getRawAxis(PS4.LEFT_TRIGGER) > 0){
                joystickValue = Robot.oi.manipulator.getRawAxis(PS4.LEFT_TRIGGER);
            }   

            else {
                joystickValue = Robot.oi.manipulator.getRawAxis(PS4.RIGHT_TRIGGER);
            }

            programTime = timeSinceInitialized();

            if (Robot.oi.manipulator.getRawAxis(PS4.RIGHT_TRIGGER) > 0) {
                Robot.intake.HPRollers(speedCap, RollerState.EJECT);
            }

            else if (Robot.oi.manipulator.getRawAxis(PS4.LEFT_TRIGGER) > 0 ){
                Robot.intake.HPRollers(speedCap, RollerState.INTAKE);
            }

            new RumbleNo(Robot.oi.manipulator).start();
        }
}

  

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Robot.logEnd(this);
    }
}
