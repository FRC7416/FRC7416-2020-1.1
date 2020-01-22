/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;
import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.Timer;


public class Buttons extends Command {
  public Buttons() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.buttonHelper);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
      Robot.m_oi.count=0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //adds ball count to smart dashboard
    SmartDashboard.putNumber("count", Robot.m_oi.count);

    if (Robot.buttonHelper.getButton(Robot.buttonHelper.button1Trigger))
    {
      if (Robot.m_oi.count < 5)
      {
        Timer.delay(.5);// if the balls go in too fast change this number
        Robot.m_oi.count ++;
      }
      else
      {
        //stop the the sucking motor
        //motor.set(0);
      }
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
