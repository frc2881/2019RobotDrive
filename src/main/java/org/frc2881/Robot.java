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

import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.frc2881.commands.scoring.AutonomousCommand;
import org.frc2881.subsystems.Arm;
import org.frc2881.subsystems.Drive;
import org.frc2881.subsystems.Intake;
import org.frc2881.subsystems.Lift;
import org.frc2881.subsystems.Pneumatics;
import org.frc2881.subsystems.PrettyLights;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static java.util.stream.Collectors.joining;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in 
 * the project.
 */
public class Robot extends TimedRobot {

	Command autonomousCommand;
    SendableChooser<Command> chooser = new SendableChooser<>();

    public static OI oi;
    public static Drive drive;
    public static Lift lift;
    public static Intake intake;
    public static Arm arm;
    public static Pneumatics pneumatics;
    public static PrettyLights prettyLights;
    private static long startTime = System.currentTimeMillis();

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {

        drive = new Drive();
        lift = new Lift();
        SmartDashboard.putData(lift);
        intake = new Intake();
        arm = new Arm();
        pneumatics = new Pneumatics();
        prettyLights = new PrettyLights();

        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();

        // Add commands to Autonomous Sendable Chooser

        chooser.setDefaultOption("Autonomous Command", new AutonomousCommand());

        SmartDashboard.putData("Auto mode", chooser);
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    @Override
    public void disabledInit(){

    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        autonomousCommand = chooser.getSelected();
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        printRobotMode("Enabled", "-");
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    private void printRobotMode(String message, String lineChar) {
        String line = IntStream.range(0, 40 - message.length()).mapToObj(n -> lineChar).collect(joining());
        System.err.println(message + line);
    }

    public static void log(String message) {
        long time = System.currentTimeMillis() - startTime;
        System.out.printf("[%6.2f] %s%n", time / 1000.0, message);
    }

    public static void logInitialize(Command command) {
        log("Command " + command.getClass().getSimpleName() + " started");
    }

    public static void logInitialize(Command command, Object... settings) {
        log("Command " + command.getClass().getSimpleName() + " started: " +
                Stream.of(settings).map(Object::toString).collect(joining(", ")));
    }

    public static void logEnd(Command command) {
        log("Command " + command.getClass().getSimpleName() + " ended");
    }

    public static void logInterrupted(Command command) {
        log("Command " + command.getClass().getSimpleName() + " interrupted");
    }
}
