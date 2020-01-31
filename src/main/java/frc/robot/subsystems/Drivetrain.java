
package frc.robot.subsystems;


import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.*;

public class Drivetrain extends Subsystem {
  //initialize the VictorSPX motor controllers for the left and right drive train
  //both motors on each side are controlled by one controller, since they should always output the same power
  //If you switch to talon, since they have encoders, you will have to switch to the talon class here. I am unsure what implications that has on the rest of the code
  private VictorSP leftMain = new VictorSP(RobotMap.LEFT_MAIN_MOTOR);
  private VictorSP rightMain = new VictorSP(RobotMap.RIGHT_MAIN_MOTOR);
  

  public Drivetrain (){

    //one motor must be inverted for the robot to drive forward, as they are identical drive trains, one mounted "backwards"
    //switch these to switch which was is forward
    leftMain.setInverted(false);
    rightMain.setInverted(true);
  
  } 

  /**
   * This method handles math for turning, than passes the new values into drive()
   * @param throttle The Y axis value from a Joystick
   * @param turn The X axis value from a Joystick
   */
  public void driveArcade(double throttle, double turn) {
    //this method does all the math for driving, both forward and turning
    
    drive(throttle + turn, throttle - turn);
    
  }

    

  /**
  * This method sets the power for the drive motors
  * @param left The power for the left motors, between 1 and -1
  * @param right The power for the right motors, between 1 and -1
  */
  public void drive(double left, double right)
  {
    //this method takes the numbers calculated from driveArcade and actually sets the values to the motors.
    leftMain.set(left);
    
    rightMain.set(right);
    
    SmartDashboard.putNumber("Right Drive Speed", right);
    SmartDashboard.putNumber("Left Drive SPeed", left);
  }
  @Override
    public void initDefaultCommand() {
      setDefaultCommand(new DriveArcade());
    }
    
}
