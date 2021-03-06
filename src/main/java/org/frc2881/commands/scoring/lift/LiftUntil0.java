// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.frc2881.commands.scoring.lift;

import org.frc2881.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftUntil0 extends Command {
    
    private double goal;

    public LiftUntil0(double goal) {
        requires(Robot.lift);
        this.goal = goal;
    }

    @Override
    protected void initialize() {
        Robot.logInitialize(this);
    }

    @Override
    protected void execute() {
        Robot.logging.traceMessage("Roll: " + Robot.drive.navX.getRoll());
        Robot.lift.setLiftMotors(1);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return Robot.drive.navX.getRoll() >= goal - 1;
    }

    @Override
    protected void end() {
        Robot.lift.setLiftMotors(0);
        Robot.logEnd(this);
    }

    @Override
    protected void interrupted() {
        Robot.lift.setLiftMotors(0);
        Robot.logInterrupted(this);
    }

}
