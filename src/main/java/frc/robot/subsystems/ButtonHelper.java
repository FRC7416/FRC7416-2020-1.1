/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;


import edu.wpi.first.wpilibj.AnalogInput;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.commands.Buttons;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class ButtonHelper extends Subsystem {
 
  public AnalogInput button1Trigger = new AnalogInput(3);



  double init= button1Trigger.getAverageVoltage();
  boolean pressed = false;
  //gets and returns button states
  /**
   * 
   * @param analog input, trigger
   * @return button state
   */
  public boolean getButton(AnalogInput trigger)
  {
    double after = trigger.getAverageVoltage();
   //SmartDashboard.putNumber("init", init); 
   //SmartDashboard.putNumber("after", after);
    if (init > after && !pressed)
    {
      pressed = true;
      return true;
    }
    else if(init < after && pressed)
    {
      pressed = false;
    }
    return false;
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new Buttons());
    
  }
}
