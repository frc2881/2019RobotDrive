// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.frc2881.commands.basic.drive;

import org.frc2881.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class HDriveCmdGroup extends CommandGroup {

    public HDriveCmdGroup() {
        addSequential(new SlowStrafe(0.065));
        addSequential(new DriveForDistance(3));
    }

    @Override
    protected void initialize() {
        Robot.logInitialize(this);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }
    
    @Override
    protected void interrupted() {
        end();
    }

    @Override
    protected void end() {
        Robot.arm.setArmMotorSpeed(0);
        Robot.lift.setLiftMotors(0);
        Robot.drive.setLiftCrawler(0);
        Robot.logEnd(this);
    }

}