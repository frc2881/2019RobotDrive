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

import java.util.function.Supplier;

import org.frc2881.commands.basic.CameraSwitch;
import org.frc2881.commands.basic.background.NavXReset;
import org.frc2881.commands.basic.background.RobotPrep;
import org.frc2881.commands.basic.background.TWINKLES;
import org.frc2881.commands.basic.drive.DriveForward;
import org.frc2881.commands.basic.drive.DriveWithJoysticks;
import org.frc2881.commands.basic.drive.IntakeSetAsBack;
import org.frc2881.commands.basic.drive.IntakeSetAsFront;
import org.frc2881.commands.basic.rumble.RumbleDriver;
import org.frc2881.commands.basic.rumble.RumbleJoysticks;
import org.frc2881.commands.basic.rumble.RumbleNo;
import org.frc2881.commands.basic.rumble.RumbleYes;
import org.frc2881.commands.basic.wait.DoNothing;
import org.frc2881.commands.basic.wait.WaitForPressure;
import org.frc2881.commands.basic.wait.WaitForever;
import org.frc2881.commands.basic.wait.WaitUntilHPDetected;
import org.frc2881.commands.basic.wait.WaitUntilNavXDetected;
import org.frc2881.commands.scoring.AutonomousCommand;
import org.frc2881.commands.scoring.HabEscape;
import org.frc2881.commands.scoring.arm.ArmCalibrateEncoder;
import org.frc2881.commands.scoring.arm.ArmControl;
import org.frc2881.commands.scoring.arm.ArmToHeight;
import org.frc2881.commands.scoring.cargo.CargoControlRollers;
import org.frc2881.commands.scoring.cargo.CargoIntake;
import org.frc2881.commands.scoring.cargo.CargoPlace;
import org.frc2881.commands.scoring.cargo.CargoSetRollers;
import org.frc2881.commands.scoring.hp.HPControlRollers;
import org.frc2881.commands.scoring.hp.HPIntakeHuman;
import org.frc2881.commands.scoring.hp.HPPlace;
import org.frc2881.commands.scoring.hp.HPSetRollers;
import org.frc2881.commands.scoring.hp.HPTongue;
import org.frc2881.commands.scoring.lift.ArmExtension;
import org.frc2881.commands.scoring.lift.ArmUntil15;
import org.frc2881.commands.scoring.lift.HabThree;
import org.frc2881.commands.scoring.lift.HabTwo;
import org.frc2881.commands.scoring.lift.LiftControl;
import org.frc2881.commands.scoring.lift.LiftControlBack;
import org.frc2881.commands.scoring.lift.LiftToHeight;
import org.frc2881.commands.scoring.lift.LiftUntil0;
import org.frc2881.commands.scoring.lift.SetCrawler;
import org.frc2881.controllers.PS4;
import org.frc2881.subsystems.Arm;
import org.frc2881.subsystems.Arm.ArmValue;
import org.frc2881.subsystems.Drive.ArmExtensionState;
import org.frc2881.subsystems.Intake.RollerDirection;
import org.frc2881.subsystems.Intake.TongueState;
import org.frc2881.utils.ButtonFromPOV;
import org.frc2881.subsystems.Lift;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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

    public static final double DEADBAND = 0.06; // 0.06;

    public enum TriggerButtons {WAIT_UNTIL_HP_DETECTED, LIFT_HP}
    public Button setIntakeFront;
    public Button setIntakeBack;
    public Button liftCrawler;
    public Button liftPin;
    public Button lowLift;
    public Button highLift;
    public Button switchCamera;
    public Button liftControl;
    public Button placeCargo;
    public Button intakeCargo;
    public Button placeHP;
    public Button lowGoal;
    public Button mediumGoal;
    public Button highGoal;
    public Button intakeHPHuman;
    public Button hPSuction;
    public Button habEscape;
    public Button lowerLift;
    public Button liftLift;
    public Button liftAutomatedHabThree;
    public Button liftAutomatedHabTwo;    
    public Button setArmExtension;
    public Button driveBackward;
    public Button hpTongue;
    public XboxController driver;
    public XboxController manipulator;

    public OI() {

        manipulator = new XboxController(2);

        driver = new XboxController(1);

        //DRIVER (DO NOT ADD MORE POV BUTTONS!!!)

        //switches camera front to back & vice versa
        switchCamera = new JoystickButton(driver, PS4.RIGHT_BUMPER);
        switchCamera.whenPressed(new CameraSwitch());

        driveBackward = new JoystickButton(driver, PS4.LEFT_BUMPER);
        driveBackward.whileHeld(new DriveForward(-0.5));

        /*liftLift = buttonFromAxis(driver, PS4.RIGHT_TRIGGER);
        liftLift.whileHeld(new LiftControlBack());
        
        //controls lift
        liftControl = buttonFromAxis(driver, PS4.LEFT_TRIGGER);
        liftControl.whileHeld(new LiftControl());
*/



        //Climbs to two platform
        liftAutomatedHabTwo = buttonFromPOVClimb(driver, 180);
        liftAutomatedHabTwo.whileHeld(new HabTwo());

        habEscape = new JoystickButton(driver, PS4.PINK_SQUARE);
        habEscape.whileHeld(new HabEscape());



        //Climbs to high platform
    //    threeLift = buttonFromPOV(driver, 0);
    //    threeLift.whileHeld(new LiftToHeight(Lift.HAB_THREE_HEIGHT, true));
        //Climbs to third platform
        highLift = buttonFromPOVClimb(driver, 0);
        highLift.whileHeld(new HabThree());
      
        setArmExtension = new JoystickButton(driver, PS4.RED_CIRCLE);
        setArmExtension.whenPressed(new ArmExtension(ArmExtensionState.BUTTON));


        //Climbs to middle platform KINDA NOT NECESSARY RN
    //    twoLift = buttonFromPOV(driver, 180);
    //    twoLift.whileHeld(new LiftToHeight(Lift.HAB_TWO_HEIGHT, true));


        //sets intake as back
        setIntakeBack = new JoystickButton(driver, PS4.BLUE_X);
        setIntakeBack.whenPressed(new IntakeSetAsBack());

        //sets intake as front
        setIntakeFront = new JoystickButton(driver, PS4.GREEN_TRIANGLE);
        setIntakeFront.whenPressed(new IntakeSetAsFront());

        //MANIPULATOR
        


        //intakes HP from human player
        intakeHPHuman = buttonFromAxis(manipulator, PS4.RIGHT_TRIGGER);
        intakeHPHuman.whenPressed(new HPIntakeHuman());

        //Sets Arm to low goal;
        lowGoal = new ButtonFromPOV(manipulator, 180);
        lowGoal.whileHeld(new ArmToHeight(ArmValue.BUTTON, Arm.LOW_GOAL, true));

        //Sets Arm to middle goal
        mediumGoal = new ButtonFromPOV(manipulator, 90);
        mediumGoal.whileHeld(new ArmToHeight(ArmValue.BUTTON, Arm.MEDIUM_GOAL, true));

        //Sets arm to high goal
        highGoal = new ButtonFromPOV(manipulator, 0);
        highGoal.whileHeld(new ArmToHeight(ArmValue.BUTTON, Arm.HIGH_GOAL, true));

        //scores HP
        placeHP = new JoystickButton(manipulator, PS4.RIGHT_BUMPER);
        placeHP.whenPressed(new HPPlace());

        //scores Cargo
        placeCargo = new JoystickButton(manipulator, PS4.LEFT_BUMPER);
        placeCargo.whileHeld(new CargoPlace());

        //intake Cargo
        intakeCargo = buttonFromAxis(manipulator, PS4.LEFT_TRIGGER);
        intakeCargo.whileHeld(new CargoIntake());

        //tongue
        hpTongue = new JoystickButton(manipulator, PS4.GREEN_TRIANGLE);
        hpTongue.whenPressed(new HPTongue(TongueState.BUTTON));


        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
        SmartDashboard.putData("Drive With Joysticks", new DriveWithJoysticks());
        SmartDashboard.putData("Arm Calibrate Encoder", new ArmCalibrateEncoder());
        SmartDashboard.putData("Arm Control", new ArmControl());
        SmartDashboard.putData("Arm To Height", new ArmToHeight(ArmValue.VALUE, Arm.HP_MEDIUM_GOAL_HEIGHT, true));
        SmartDashboard.putData("Cargo Control Rollers", new CargoControlRollers());
        SmartDashboard.putData("Cargo Placed", new CargoPlace());
        SmartDashboard.putData("Cargo Set Rollers", new CargoSetRollers(0.5, RollerDirection.EJECT));
        SmartDashboard.putData("Cargo Intake", new CargoIntake());
        SmartDashboard.putData("Do Nothing", new DoNothing());
        //SmartDashboard.putData("Drive Forward", new DriveForward(0.5));
        SmartDashboard.putData("Arm Until 15", new ArmUntil15(15));
        SmartDashboard.putData("Lift Until 0", new LiftUntil0(0));
        SmartDashboard.putData("HP Placed", new HPPlace());
        SmartDashboard.putData("HP Set Rollers", new HPSetRollers(0.5, RollerDirection.EJECT));
        SmartDashboard.putData("HP Control Rollers", new HPControlRollers());
        SmartDashboard.putData("HP Intake Human", new HPIntakeHuman());
        SmartDashboard.putData("HP Tongue", new HPTongue(TongueState.BUTTON));
        SmartDashboard.putData("Lift Crawler", new SetCrawler(1));
        SmartDashboard.putData("Robot Prep", new RobotPrep());
        SmartDashboard.putData("NavX Reset", new NavXReset());
        SmartDashboard.putData("Rumble Driver", new RumbleDriver());
        SmartDashboard.putData("Rumble Joysticks", new RumbleJoysticks());
        SmartDashboard.putData("Rumble Yes", new RumbleYes(driver));
        SmartDashboard.putData("Rumble No", new RumbleNo(driver));
        SmartDashboard.putData("Intake Set As Back", new IntakeSetAsBack());
        SmartDashboard.putData("Intake Set As Front", new IntakeSetAsFront());
        SmartDashboard.putData("TWINKLES", new TWINKLES());
        SmartDashboard.putData("Wait Forever", new WaitForever());
        SmartDashboard.putData("Wait For Pressure", new WaitForPressure());
        SmartDashboard.putData("Wait Until HP Detected", new WaitUntilHPDetected());
        SmartDashboard.putData("Wait Until NavX Detected", new WaitUntilNavXDetected());
        SmartDashboard.putData("Camera Switch", new CameraSwitch());

    }

    public XboxController getDriver() {
        return driver;
    }

    public XboxController getManipulator() {
        return manipulator;
    }

    private Button buttonFromPOVClimb(GenericHID controller, int angle) {
        return new Button() {
            @Override
            public boolean get() {
                if (angle == 0) {
                    return (controller.getPOV() == 0) || (controller.getPOV() == 45) || (controller.getPOV() == 315);
                }
                else {
                    return (controller.getPOV() == 180) || (controller.getPOV() == 225) || (controller.getPOV() == 135);
                }
            }
        };
    }

    /*with XboxController, there isn't a way to just see if a trigger axis button is pressed, so this method turns it
    into a button from an axis*/
    private Button buttonFromAxis(GenericHID controller, int axis) {
        return new Button() {
            @Override
            public boolean get() {
                return Math.abs(controller.getRawAxis(axis)) > DEADBAND;
            }
        };
    }

        public static double squareInput(double speed) {
        return Math.copySign(speed * speed, speed);
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

    private Supplier<TriggerButtons> buttonFromAxisRange(GenericHID controller, int axis) {
        return () -> {
            if (Math.abs(controller.getRawAxis(axis)) <= 0.5) {
                return TriggerButtons.WAIT_UNTIL_HP_DETECTED;
            }

            return TriggerButtons.LIFT_HP;
        };
    }

}

