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

import org.frc2881.Robot;
import org.frc2881.subsystems.Intake.GrabberState;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 *
 */
public class HPGrabber extends InstantCommand {
    private final GrabberState state;

    public HPGrabber(GrabberState state) {
        super("HPGrabber" + (state == GrabberState.GRAB ? "Grab" : "Release"));
       // requires(Robot.intakeSubsystem); We don't need this because we need the rollers to run at the same time as the Grabber.
        this.state = state;
    }
    
    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        //this turns the piston to true/extended
        Robot.intake.setHPGrabber(state);
    }

    @Override
    protected void end() {
        Robot.log("Set HPGrabber has ended: " + state);
    }

}