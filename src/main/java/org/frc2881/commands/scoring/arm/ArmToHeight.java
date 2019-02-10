// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.frc2881.commands.scoring.arm;

import edu.wpi.first.wpilibj.command.Command;

import org.frc2881.Robot;
import org.frc2881.commands.basic.rumble.RumbleYes;

/**
 *
 */
public class ArmToHeight extends Command {

    private double height;
    private boolean rumble;

    public ArmToHeight(double height, boolean rumble) {
        requires (Robot.arm);
        this.height = height;
        this.rumble = rumble;
    }

    @Override
    protected void initialize() {
        Robot.logInitialize(this, height);
        Robot.arm.setArmDesiredHeight(this.height);
        Robot.arm.enable();
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return Robot.arm.onTarget() || timeSinceInitialized() >= 2;
    }

    @Override
    protected void end() {
        Robot.arm.disable();
        if (rumble) {
            new RumbleYes(Robot.oi.manipulator).start();
        }
        Robot.logEnd(this);
    }

    @Override
    protected void interrupted() {
        Robot.arm.disable();
        Robot.arm.setArmMotorSpeed(0);
        Robot.logInterrupted(this);
    }

}
