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
import org.frc2881.commands.basic.rumble.RumbleNo;
import org.frc2881.utils.AmpMonitor;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftSet extends Command {

    private AmpMonitor ampMonitor = new AmpMonitor(20, () -> Robot.lift.getLiftMotorCurrent());
    private boolean rumbled;
    private boolean lift;
    private double speed;

    public LiftSet(boolean lift, double speed) {
        requires(Robot.lift);
        this.speed = speed;
    }

    @Override
    protected void initialize() {
        Robot.logInitialize(this);
        ampMonitor.reset();
        rumbled = false;
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {

        if (lift) {
            Robot.lift.setLiftMotors(speed);
        } else {
            Robot.lift.setLiftMotors(-speed);
        }


        if (ampMonitor.isTriggered()) {
            
            if (!rumbled) {         
                new RumbleNo(Robot.oi.manipulator).start();
                Robot.log("Lift current limit exceeded");
                rumbled = true;
            }

            Robot.lift.setLiftMotors(0);   
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Robot.lift.setLiftMotors(0);
        Robot.logEnd(this);
    }

}