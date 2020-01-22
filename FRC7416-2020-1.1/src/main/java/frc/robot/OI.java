
  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new Exam*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
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
  // button.whenPressed(new ExamppleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());

  /**
     * The right flight stick, a Thrustmaster T-16000.
     */
    public Joystick rightFStick = new Joystick(0);

    /**
     * The left flight stick, a Logitech Extreme 3d Pro. 
     */
    public Joystick leftFStick = new Joystick(1);

    //"default" deadband values
    //was set to driver preference for 2019 season. If controlers change or driver doesn't like the feel
    //feel free to tmess around, but don't set to zero or errors may occur. Changing this value effects the entire sensitivity curve
    //You can pass this or any value into getAxis
    public final double RIGHT_FSTICK_DEADBAND = 0.05;
    public final double LEFT_FSTICK_DEADBAND = 0.001;

    
    /**The multiplier for the Drivetrain. Speed is multiplied by this double before being passed into the drive methods
     * Is initialized at the lowest speed, .25, the speed used during Sandstorm
    */
    public double multiplier = .25;




    public Button leftFStickButton2 = new JoystickButton(leftFStick, 2);
    public Button leftFStickButton3 = new JoystickButton(leftFStick, 3);
   
    // not sure if this is how I make a button
    public int count= 0;
   

   
    /** 
    *  This value tests whether a value from a joystick is within it's deadband. 
    *  @param value the axis value being tested
    *  @param deadband the deadband value 
    *  @param center the center of the joystick. This almost always be 0.0
    *  @return If the value is within the deadband, returns 0.0. Otherwise will return the original value unmodified
    */
    private static double fStickDeadband(double value, double deadband, double center) {
        return (value < (center + deadband) && value > (center - deadband)) ? center : value;
        }

    /** This method is used to read values from any axis on any controller
    * @param fStick specifies which Joystick object is being read 
    * @param axis specifies which axis from the Joystick should be read 
    * @param deadband the deadband that will be applied to this input
    * @return the value of the controller, between -1 and 1
    */
    public double getAxis(Joystick fStick, int axis, double deadband )
    {
        return fStickDeadband(fStick.getRawAxis(axis), deadband, 0.0);
    }

    /** This method is used to read hat switch values from any Joystick object
     * @param fStick The joystick that will be read
     * @return The value of the hat switch in degrees (45 degree intervals). Will return -1 if position is neutral.
     * 
    */
    public int getHatState(Joystick fStick){
        return fStick.getPOV();
    }


    /** Uses getHatState to read a hat's values, then interprets it into a speed value.
     * Not currently used in this branch of the code, but I left this because it will likely be used
     * @param fStick The joystick which will have it's hat switch read
     * @param forward The hat switch value (in degrees) for the first speed
     * @param back  The hat switch value (in degrees) for the second speed
     * @param speed0 The speed value for the first speed (between -0 and 1)
     * @param speed1 The speed value for the second speed (between -1 and 1)
     * @return A speed value between -1 and 1
     */
    public double interpretHatState(Joystick fStick, int forward, int back, double speed0, double speed1){
        double speed = 0.0;
        if (getHatState(fStick) == forward)
            speed = speed0;
        else if (getHatState(fStick) == back)
            
            speed = speed1;
        return speed;
    }

    /**
     * This method is used to read buttons from Joysticks
     * Consider depricating and replacing with Button objects, allowing more versitile control
     * The sooner you do do this the easier it will be
     * @param fStick The Joystick object the method reads values from
     * @param map The index of the button that will be read
     * @return True if button is pressed, otherwise False
     */
    public boolean getButtonPressed(Joystick fStick, int map){
        return fStick.getRawButton(map);
    }

    
    /**
     * This method is used to read buttons from Joysticks
     * @param fStick The Joystick object the method reads the vaules from
     * @return True if the button is pressed, otherwise False
     */
    public boolean getTState(Joystick fStick){
        return fStick.getTrigger();
    
    }

    /**This method is used to change the speed multiplier for the robot.
     * <p>If more than one button is being held down, the highest one will override the rest.
     * @param speedOneButton the boolean value of the button being tested for the lowest speed, .25
     * @param speedTwoButton the boolean value of the button being tested for the middle speed, .5
     * @param speedThreeButton the boolean value of the button being tested for the highest speed, .75
     * @return The multiplier value for the speed, either .25, .5, or .75
     */
    public double getMultiplier(boolean speedOneButton, boolean speedTwoButton, boolean speedThreeButton, boolean speedFourButton){
        if (speedOneButton == true){
            multiplier = .25;
        }
        if (speedTwoButton == true){
            multiplier = .5;
        }
        if (speedThreeButton == true){
            multiplier = .75;
        }
        if (speedFourButton == true){
            multiplier = 1;
        }
        return multiplier;
    }

}

