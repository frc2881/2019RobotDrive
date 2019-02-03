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

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import org.frc2881.OI;
import org.frc2881.RobotType;
import org.frc2881.commands.basic.drive.DriveWithJoysticks;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.SendableBase;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

/**
 *
 */
public class Drive extends Subsystem {

    public enum IntakeLocation {
        FRONT, BACK
    }

    private DifferentialDrive differentialDrive;

    //Intake front and back
    private IntakeLocation intakeLocation = IntakeLocation.FRONT;

    public Drive() {
        SpeedController left;
        SpeedController right;

        if (RobotType.get() == RobotType.COMPETITION_BOT) {
            CANSparkMax leftFront = addDevice("Left Front", new CANSparkMax(1, MotorType.kBrushless));
            CANSparkMax leftBack = addDevice("Left Back", new CANSparkMax(2, MotorType.kBrushless));
            CANSparkMax rightFront = addDevice("Right Front", new CANSparkMax(3, MotorType.kBrushless));
            CANSparkMax rightBack = addDevice("Right Back", new CANSparkMax(4, MotorType.kBrushless));

            left = addDevice("Left", new SpeedControllerGroup(leftFront, leftBack));
            right = addDevice("Right", new SpeedControllerGroup(rightFront, rightBack));

        } else if (RobotType.get() == RobotType.TEST_BOARD_1) {
            WPI_TalonSRX leftFront = addDevice("Left Front", new WPI_TalonSRX(1));
            WPI_TalonSRX leftBack = addDevice("Left Back", new WPI_TalonSRX(2));
            WPI_TalonSRX rightFront = addDevice("Right Front", new WPI_TalonSRX(3));
            Spark rightBack = addDevice("Right Back", new Spark(8));  // SparkMAX on PWM

            left = addDevice("Left", new SpeedControllerGroup(leftFront, leftBack));
            right = addDevice("Right", new SpeedControllerGroup(rightFront, rightBack));

        } else {
            left = addDevice("Left", new WPI_TalonSRX(1));
            right = addDevice("Right", new WPI_TalonSRX(2));
        }

        // Left and right are oriented in opposite ways so one is usually inverted
        right.setInverted(true);
        
        differentialDrive = new DifferentialDrive(left, right);
        addChild("Differential Drive",differentialDrive);

        differentialDrive.setSafetyEnabled(true);
        differentialDrive.setExpiration(0.1);
        differentialDrive.setMaxOutput(1.0);
    }

    public void setIntakeLocation(IntakeLocation intakeLocation) {
        this.intakeLocation = intakeLocation;
    }

    public void tankDrive(double leftSpeed, double rightSpeed) {
        // Use 'squaredInputs' to get better control at low speed
        differentialDrive.setDeadband(OI.DEADBAND);
        if (intakeLocation == IntakeLocation.FRONT) {
            differentialDrive.tankDrive(leftSpeed, rightSpeed, true);
        } else {
            differentialDrive.tankDrive(-rightSpeed, -leftSpeed, true);
        }
    }

    @Override
    public void initDefaultCommand() {
        setDefaultCommand(new DriveWithJoysticks());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

	public double getLeftDistance() {
		return 0;
	}

	public double getRightDistance() {
		return 0;
	}


    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private <T extends Sendable> T addDevice(String name, T device) {
        super.addChild(name, device);
        return device;
    }

    private CANSparkMax addDevice(String name, CANSparkMax spark) {
        addDevice(name, new SendableBase() {
            public void initSendable(SendableBuilder builder) {
                builder.setSmartDashboardType("Speed Controller");
                builder.setActuator(true);
                builder.setSafeState(spark::disable);
                builder.addDoubleProperty("Value", spark::get, spark::set);
            }
        });
        return spark;
    }
}
