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


import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;



/**
 *
 */
public class PrettyLights extends Subsystem {



    private Spark tWINKLES;


    public PrettyLights() {
        tWINKLES = new Spark(2);
        addChild("TWINKLES",tWINKLES);
        tWINKLES.setInverted(false);
        

    }

    @Override
    public void initDefaultCommand() {



        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }




    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}

