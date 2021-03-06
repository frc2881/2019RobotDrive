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

import org.frc2881.commands.scoring.cargo.CargoIntake;
import org.frc2881.utils.frc4048.Logging;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DigitalInput;

/**
 *
 */
public class Intake extends Subsystem {

    public enum GrabberState {GRAB, RELEASE, BUTTON}
    public enum SuctionState {OPEN, CLOSED, BUTTON}
    public enum RollerDirection {INTAKE, EJECT, BUTTON}
    public enum TongueState {IN, OUT, BUTTON}

    private final PowerDistributionPanel pdp = new PowerDistributionPanel(10);
    private Spark cargoIntakeMotor;
    private int intakecargoRollerPdpChannel = 6;
    private Solenoid hPSuctionCup;
    private Solenoid hPGrabber;
    private Solenoid hPTongue;
    private Spark hPIntakeMotor;
    private DigitalInput hPDetector;

    public static double HP_DETECTED_SPEED = 0.2;
    private int intakeHPRollerPdpChannel = 1;

    public Intake() {
        
        cargoIntakeMotor = new Spark(4);
        addChild("Cargo Intake Motor",cargoIntakeMotor);
        cargoIntakeMotor.setInverted(false);

        hPSuctionCup = new Solenoid(11, 1);
        addChild("HP Suction Cup Solenoid",hPSuctionCup);

        hPGrabber = new Solenoid(11, 2);
        addChild("HP Grabber Solenoid",hPGrabber);
        
        hPTongue = new Solenoid(11, 4);
        addChild("HP Tongue Solenoid",hPTongue);

        hPIntakeMotor = new Spark(3);
        addChild("HP Intake Motor",hPIntakeMotor);
        hPIntakeMotor.setInverted(false);
        
        //on is unblocked, off is blocked
        hPDetector = new DigitalInput(5);
        addChild("HP Infrared Detector", hPDetector);
    }

    @Override
    public void initDefaultCommand() {
        
    }

    public final Logging.LoggingContext loggingContext = new Logging.LoggingContext(Logging.Subsystems.INTAKE) {

        @Override
        protected void addAll() {
            add("Cargo Intake Motor Speed", cargoIntakeMotor.getSpeed());
            add("HP Intake Motor Speed", hPIntakeMotor.getSpeed());
            add("HP Suction State", getSuctionState());
            add("HP Grabber State", getGrabberState());
        }
    };

    //Suction Cups
    public void suction(SuctionState state) {
        if (state == SuctionState.BUTTON) {
            hPSuctionCup.set(!hPSuctionCup.get());

        } else {
            hPSuctionCup.set(state == SuctionState.OPEN);
        }
    }

    public SuctionState getSuctionState(){
        if (hPSuctionCup.get()){
            return SuctionState.OPEN;
        }
        else {
            return SuctionState.CLOSED;
        }
    }

    //Tongue
    public void tongue(TongueState state) {
        if (state == TongueState.BUTTON) {
            hPTongue.set(!hPTongue.get());

        } else {
            hPTongue.set(state == TongueState.OUT);
        }
    }

    public TongueState getTongueState() {
        if (hPTongue.get()){
            return TongueState.IN;
        }
        else {
            return TongueState.OUT;
        }
    }


    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

    public boolean isHPDetected(){
        //true is not detected (no hatch panel)
        return !hPDetector.get();
    }
    
    public double getCargoRollerCurrent(){
        return pdp.getCurrent(intakecargoRollerPdpChannel);
    }

    public void cargoRollers(double speed, RollerDirection state) {
        //POSITIVE IS EJECTING
        if (state == RollerDirection.EJECT) {
            cargoIntakeMotor.set(speed);
        } else if (state == RollerDirection.INTAKE){
            cargoIntakeMotor.set(-speed);
        }
        else {
            cargoIntakeMotor.set(speed);
        }
    }

    public boolean getCargoRollers(){
        return (cargoIntakeMotor.get() >= 0.05);
    }

    //Stops the rollers (put at the end of the command)
    public void stopCargoRollers() {
        cargoIntakeMotor.set(0);
    }

    public double getHPRollerCurrent(){
        return pdp.getCurrent(intakeHPRollerPdpChannel);
    }

    public void HPRollers(double speed, RollerDirection state) {
        //POSITIVE IS EJECTING
        if (state == RollerDirection.EJECT) {
            hPIntakeMotor.set(speed);
        } else if (state == RollerDirection.INTAKE){
            hPIntakeMotor.set(speed);
        }
        else {
            hPIntakeMotor.set(-speed);
        }
    }

    public boolean getHPRollers(){
        return (hPIntakeMotor.get() >= 0.05);
    }

    //Stops the rollers (put at the end of the command)
    public void stopHPRollers() {
        hPIntakeMotor.set(0);
    }

    //Grabber
    public void setHPGrabber(GrabberState state) {
        if (state == GrabberState.BUTTON) {
            hPGrabber.set(!hPGrabber.get());

        } else {
            hPGrabber.set(state == GrabberState.GRAB);
        }
    }

    public GrabberState getGrabberState(){
        if (hPGrabber.get()){
            return GrabberState.RELEASE;
        }
        else {
            return GrabberState.GRAB;
        }
    }
}
