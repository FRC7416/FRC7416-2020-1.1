/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/


package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.FStickMap;

/**
 * The DriveAracade Command class runs constantly, processing inputs to run the main motors. 
 * <p> Responsible for Speed, Turn, Reverse, Lift Lock, and Speed Multiplier
 */
public class DriveArcade extends Command {
  
  public DriveArcade() {
    requires(Robot.drivetrain);
  }

  @Override
  protected void initialize() {
  }

  //This execute command handles most of our main drive functions. 
  @Override
  protected void execute() {

    //Here we control the multipler for the speed, comparable to "gears" in a car.
    final double multiplier = Robot.m_oi.getMultiplier(
      Robot.m_oi.getButtonPressed(Robot.m_oi.rightFStick, FStickMap.BUTTON2),
      Robot.m_oi.getButtonPressed(Robot.m_oi.rightFStick, FStickMap.BUTTON3), 
      Robot.m_oi.getButtonPressed(Robot.m_oi.rightFStick, FStickMap.BUTTON4),
      Robot.m_oi.getButtonPressed(Robot.m_oi.rightFStick, FStickMap.BUTTON5));
    
    //This should place the information onto smartdashboard, but it is not currently working for unknown reasons.
    SmartDashboard.putNumber("Speed Multiplier", multiplier);
   

    //Main drive function. This takes the axis inputs from our joysticks, and then mulitplies it by:
    //multiplier: the speed multiplier, .25, .5, .75, or 1
    //Robot.oi.reverse 1 or -1
    Robot.drivetrain.driveArcade(
      multiplier * Robot.m_oi.getAxis(Robot.m_oi.rightFStick, FStickMap.YAXIS, Robot.m_oi.RIGHT_FSTICK_DEADBAND),
      multiplier * Robot.m_oi.getAxis(Robot.m_oi.leftFStick, FStickMap.XAXIS, Robot.m_oi.LEFT_FSTICK_DEADBAND));
    
  }

   
  @Override
  protected boolean isFinished() {
    //Returns false, so this command loops eternally.
    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
