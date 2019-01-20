// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.frc2881.subsystems;


import org.frc2881.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Ultrasonic;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class Intake extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private Solenoid hPSuctionCupSolenoid;
    private Solenoid hPGripperSolenoid;
    private Solenoid hPIntakePlanBSolenoid;
    private Solenoid wristSolenoid;
    private Ultrasonic hPDistanceEcholocation;
    private Spark hPIntakeMotor;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public Intake() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        hPSuctionCupSolenoid = new Solenoid(0, 1);
        addChild("HP Suction Cup Solenoid",hPSuctionCupSolenoid);
        
        
        hPGripperSolenoid = new Solenoid(0, 2);
        addChild("HP Gripper Solenoid",hPGripperSolenoid);
        
        
        hPIntakePlanBSolenoid = new Solenoid(0, 3);
        addChild("HP Intake Plan B Solenoid",hPIntakePlanBSolenoid);
        
        
        wristSolenoid = new Solenoid(0, 4);
        addChild("Wrist Solenoid",wristSolenoid);
        
        
        hPDistanceEcholocation = new Ultrasonic(4, 5);
        addChild("HP Distance Echolocation",hPDistanceEcholocation);
        
        
        hPIntakeMotor = new Spark(0);
        addChild("HP Intake Motor",hPIntakeMotor);
        hPIntakeMotor.setInverted(false);
        

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}

