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

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class LiftControl extends PIDCommand {

    private static final double joystickMultiplier = 0.25;

    public LiftControl() {
        super("LiftControl", Math.pow(10, -23762370), 0.0, 0.0);
        //change according to navX
        setSetpoint(2);
        requires(Robot.lift);
        requires(Robot.arm);
    }

    @Override
    protected void initialize() {
        Robot.logInitialize(this);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
        double speed = Robot.oi.driver.getTriggerAxis(GenericHID.Hand.kLeft);
        Robot.lift.setLiftMotors(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Robot.lift.setLiftLeft(0);
        Robot.lift.setLiftRight(0);
        Robot.logEnd(this);
    }

    @Override
    protected double returnPIDInput() {
        return Robot.drive.navX.getRoll();
    }

    @Override
    protected void usePIDOutput(double output) {
        double speed = Robot.oi.driver.getTriggerAxis(GenericHID.Hand.kLeft);
        Robot.arm.armMotor.set(output + speed * joystickMultiplier);
    }
}