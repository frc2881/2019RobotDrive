// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.frc2881.commands.scoring.cargo;

import edu.wpi.first.wpilibj.command.Command;

import org.frc2881.Robot;
import org.frc2881.controllers.PS4;
import org.frc2881.subsystems.Intake.RollerDirection;

/**
 *
 */
public class CargoIntake extends Command {

    public CargoIntake() {
        requires (Robot.intake);

    }

    @Override
    protected void initialize() {
        Robot.logInitialize(this);
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {

        Robot.intake.cargoRollers(Robot.oi.manipulator.getRawAxis(PS4.LEFT_TRIGGER), RollerDirection.INTAKE);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Robot.intake.cargoRollers(0, RollerDirection.BUTTON);
        Robot.logEnd(this);
    }
} 