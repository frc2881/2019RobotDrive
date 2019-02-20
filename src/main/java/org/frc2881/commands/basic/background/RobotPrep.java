// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.frc2881.commands.basic.background;

import org.frc2881.Robot;
import org.frc2881.commands.basic.drive.IntakeSetAsFront;
import org.frc2881.commands.basic.wait.WaitForPressure;
import org.frc2881.commands.basic.wait.WaitUntilNavXDetected;
import org.frc2881.commands.scoring.arm.ArmCalibrateEncoder;
import org.frc2881.commands.scoring.hp.HPGrabber;
import org.frc2881.commands.scoring.hp.HPSuction;
import org.frc2881.subsystems.Intake.GrabberState;
import org.frc2881.subsystems.Intake.SuctionState;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class RobotPrep extends CommandGroup {

    public RobotPrep() {
        addSequential(new WaitForPressure());
        addSequential(new WaitUntilNavXDetected());
        addSequential(new HPGrabber(GrabberState.GRAB));
        addSequential(new HPSuction(SuctionState.CLOSED));
        addSequential(new IntakeSetAsFront());
        addSequential(new ArmCalibrateEncoder());
    }

    @Override
    protected void initialize() {
        Robot.logInitialize(this);
    }

    @Override
    protected void end() {
        Robot.logEnd(this);
    }
}
