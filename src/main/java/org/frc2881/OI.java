// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.frc2881;

import org.frc2881.commands.*;
import org.frc2881.commands.basic.*;
import org.frc2881.commands.basic.background.*;
import org.frc2881.commands.basic.drive.*;
import org.frc2881.commands.basic.wait.*;
import org.frc2881.commands.basic.rumble.*;
import org.frc2881.commands.scoring.*;
import org.frc2881.commands.scoring.arm.*;
import org.frc2881.commands.scoring.cargo.*;
import org.frc2881.commands.scoring.HP.*;
import org.frc2881.commands.scoring.lift.*;
import org.frc2881.controllers.PS4;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.*;
import org.frc2881.subsystems.*;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);

    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.

    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());

    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());

    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());


    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static final double DEADBAND = 0.06;

    public Button setIntakeFront;
    public Button setIntakeBack;
    public Button liftCrawler;
    public Button liftPin;
    public Button lowLift;
    public Button highLift;
    public Button switchCamera;
    public Button manipulatorGreenTriangle;
    public Button manipulatorBlueX;
    public Button loadCargo;
    public Button loadHP;
    public Button lowGoal;
    public Button mediumGoal;
    public Button highGoal;
    public Button manipulatorOption;
    public Button intakeHPHuman;
    public Button intakeHPFloor;
    public XboxController driver;
    public XboxController manipulator;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public OI() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        manipulator = new XboxController(2);
        
        driver = new XboxController(1);
        

        //DRIVER

        //switches camera front to back & vice versa
        switchCamera = new JoystickButton(driver, PS4.RIGHT_BUMPER);
        switchCamera.whenPressed(new CameraSwitch());
        
        //Climbs to high platform
        lowLift = buttonFromPOV(driver, 180);
        lowLift.whileHeld(new LiftToHeight(Lift.HIGH_PLATFORM_HEIGHT, true));

        //Climbs to low platform
        highLift = buttonFromPOV(driver, 0);
        highLift.whileHeld(new LiftToHeight(Lift.LOW_PLATFORM_HEIGHT, true));

        //lifts lift wheels up
        liftPin = new JoystickButton(driver, PS4.RED_CIRCLE);
        liftPin.whenPressed(new LiftPin());

        //drives lift wheels forward
        liftCrawler = new JoystickButton(driver, PS4.PINK_SQUARE);
        liftCrawler.whenPressed(new LiftCrawler());

        //sets intake as back
        setIntakeBack = new JoystickButton(driver, PS4.BLUE_X);
        setIntakeBack.whenPressed(new IntakeSetAsBack());

        //sets intake as front
        setIntakeFront = new JoystickButton(driver, PS4.GREEN_TRIANGLE);
        setIntakeFront.whenPressed(new IntakeSetAsFront());
       

        //MANIPULATOR

        //intakes HP from ground
        intakeHPFloor = buttonFromAxis(manipulator, PS4.RIGHT_TRIGGER);
        intakeHPFloor.whenPressed(new HPIntakeGround());

        //intakes HP from human player
        intakeHPHuman = new JoystickButton(manipulator, PS4.RIGHT_BUMPER);
        intakeHPHuman.whenPressed(new HPIntakeHuman());
        
        //Sets Arm to low goal;
        lowGoal = buttonFromPOV(manipulator, 180);
        lowGoal.whileHeld(new ArmToHeight(Arm.LOW_GOAL_HEIGHT, true));

        //Sets Arm to middle goal
        mediumGoal = buttonFromPOV(manipulator, 90);
        mediumGoal.whileHeld(new ArmToHeight(Arm.MEDIUM_GOAL_HEIGHT, true));

        //Sets arm to high goal
        highGoal = buttonFromPOV(manipulator, 0);
        highGoal.whileHeld(new ArmToHeight(Arm.HIGH_GOAL_HEIGHT, true));

        //scores HP
        loadHP = buttonFromAxis(manipulator, PS4.RED_CIRCLE);
        loadHP.whenPressed(new HPPlace());

        //scores Cargo
        loadCargo = new JoystickButton(manipulator, PS4.PINK_SQUARE);
        loadCargo.whenPressed(new CargoPlace());

        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
        SmartDashboard.putData("Drive With Joysticks", new DriveWithJoysticks());
        SmartDashboard.putData("Arm Calibrate Encoder", new ArmCalibrateEncoder());
        SmartDashboard.putData("Arm Control", new ArmControl());
        SmartDashboard.putData("Arm To Height", new ArmToHeight(Arm.MEDIUM_GOAL_HEIGHT, true));
        SmartDashboard.putData("Cargo Control Rollers", new CargoControlRollers());
        SmartDashboard.putData("Cargo Loaded", new CargoPlace());
        SmartDashboard.putData("Cargo Set Rollers", new CargoSetRollers());
        SmartDashboard.putData("Cargo Intake", new CargoIntake());
        SmartDashboard.putData("Do Nothing", new DoNothing());
        SmartDashboard.putData("Drive Forward", new DriveForward());
        SmartDashboard.putData("HP Loaded", new HPPlace());
        SmartDashboard.putData("HP Set Rollers", new HPSetRollers());
        SmartDashboard.putData("HP Control Rollers", new HPControlRollers());
        SmartDashboard.putData("HP Intake Human", new HPIntakeHuman());
        SmartDashboard.putData("HP Intake Ground", new HPIntakeGround());
        SmartDashboard.putData("Lift To Height", new LiftToHeight(Lift.LOW_PLATFORM_HEIGHT, true));
        SmartDashboard.putData("Lift Control", new LiftControl());
        SmartDashboard.putData("Lift Pin", new LiftPin());
        SmartDashboard.putData("Lift Set Screw", new LiftSetScrew());
        SmartDashboard.putData("Lift Crawler", new LiftCrawler());
        SmartDashboard.putData("Robot Prep", new RobotPrep());
        SmartDashboard.putData("NavX Reset", new NavXReset());
        SmartDashboard.putData("Rumble Driver", new RumbleDriver());
        SmartDashboard.putData("Rumble Joysticks", new RumbleJoysticks());
        SmartDashboard.putData("Rumble Yes", new RumbleYes());
        SmartDashboard.putData("Rumble No", new RumbleNo());
        SmartDashboard.putData("Intake Set As Back", new IntakeSetAsBack());
        SmartDashboard.putData("Intake Set As Front", new IntakeSetAsFront());
        SmartDashboard.putData("TWINKLES", new TWINKLES());
        SmartDashboard.putData("Wait Forever", new WaitForever());
        SmartDashboard.putData("Wait For Pressure", new WaitForPressure());
        SmartDashboard.putData("Wait Until HP Detected", new WaitUntilHPDetected());
        SmartDashboard.putData("Wait Until Cargo Detected", new WaitUntilCargoDetected());
        SmartDashboard.putData("Wait Until NavX Detected", new WaitUntilNavXDetected());
        SmartDashboard.putData("Camera Switch", new CameraSwitch());

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
    public XboxController getDriver() {
        return driver;
    }

    public XboxController getManipulator() {
        return manipulator;
    }

    private Button buttonFromPOV(GenericHID controller, int angle) {
        return new Button() {
            @Override
            public boolean get() {
                return (controller.getPOV()) == angle;
            }
        };
    }

    /*with XboxController, there isn't a way to just see if a trigger axis button is pressed, so this method turns it
    into a button from an axis*/
    private Button buttonFromAxis(GenericHID controller, int axis) {
        return new Button() {
            @Override
            public boolean get() {
                return Math.abs(controller.getRawAxis(axis)) > 0.05;
            }
        };
    }

    public static double applyDeadband(double value) {
        if (Math.abs(value) > DEADBAND) {
            if (value > 0.0) {
                return (value - DEADBAND) / (1.0 - DEADBAND);
            } else {
                return (value + DEADBAND) / (1.0 - DEADBAND);
            }
        } else {
            return 0.0;
        }
    }

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=FUNCTIONS
}
