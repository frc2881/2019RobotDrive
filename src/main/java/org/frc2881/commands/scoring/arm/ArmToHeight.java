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
import org.frc2881.subsystems.Arm;
import org.frc2881.subsystems.Arm.ArmValue;
import org.frc2881.subsystems.Arm.WristState;
import org.frc2881.subsystems.Intake.RollerDirection;
import org.frc2881.subsystems.Intake.SuctionState;

/**
 *
 */
public class ArmToHeight extends Command {

    //Code something that doesn't use pid controller and mannually ramps motor speed and compares to potentiometer/encoder value with speed min
    
    private double height;
    private boolean rumble;
    private ArmValue goal;

    public ArmToHeight(ArmValue goal, double height, boolean rumble) {
        requires(Robot.arm);
        this.height = height;
        this.rumble = rumble;
        this.goal = goal;
    }

    @Override
    protected void initialize() {

        boolean highGoal = height == Arm.HIGH_GOAL;
        boolean mediumGoal = height == Arm.MEDIUM_GOAL;
        boolean lowGoal = height == Arm.LOW_GOAL;
        boolean HPLoaded = Robot.intake.getSuctionState() == SuctionState.CLOSED;

        Robot.logInitialize(this, height);
        
        if (goal == ArmValue.BUTTON){
            if (!HPLoaded) {
                if (highGoal) {
                    Robot.arm.setArmDesiredHeight(Arm.CARGO_HIGH_GOAL_HEIGHT);
                } else if (mediumGoal) {
                    Robot.arm.setArmDesiredHeight(Arm.CARGO_MEDIUM_GOAL_HEIGHT);
                } else if (lowGoal) {
                    Robot.arm.setArmDesiredHeight(Arm.CARGO_LOW_GOAL_HEIGHT);
                }
            } else {
                if (highGoal) {
                    Robot.arm.setArmDesiredHeight(Arm.HP_HIGH_GOAL_HEIGHT);
                } else if (mediumGoal) {
                    Robot.arm.setArmDesiredHeight(Arm.HP_MEDIUM_GOAL_HEIGHT);
                } else if (lowGoal) {
                    Robot.arm.setArmDesiredHeight(Arm.HP_LOW_GOAL_HEIGHT);
                }  
                else {
                    Robot.arm.setArmDesiredHeight(this.height);
                }
            }
        }
        else {
            Robot.arm.setArmDesiredHeight(this.height);
        }
        Robot.arm.enable();

        Robot.logInitialize(this, Robot.arm.getSetpoint());

    }
    

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return Robot.arm.onTarget() || timeSinceInitialized() >= 200;
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
