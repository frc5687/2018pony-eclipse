package org.frc5687.steamworks.protobot.commands.test;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.sql.Driver;

import static org.frc5687.steamworks.protobot.Robot.ledStrip;
import static org.frc5687.steamworks.protobot.Robot.lights;
import static org.frc5687.steamworks.protobot.Robot.oi;

/**
 * Command to prompt the operator to confirm a result using the gamepad buttons.
 */
public class ConfirmTest extends Command {

    private String _message;
    private String _success;
    private String _failure;
    private boolean _clear;
    private String _key;

    public ConfirmTest(String message, String success, String failure) {
        _message = message;
        _success = success;
        _failure = failure;
        _clear = false;
    }

    public ConfirmTest(String message, String key) {
        _message = message;
        _key = key;
        _clear = false;
    }

    @Override
    protected void initialize() {
        DriverStation.reportError(_message, false);
        SmartDashboard.putString("SelfTest/Message", _message);
    }

    @Override
    protected void execute() {
        if (!_clear) {
            _clear = !(oi.isNoPressed() || oi.isYesPressed());
        }
    }

    @Override
    protected boolean isFinished() {
        if (_clear) {
            if (oi.isYesPressed()) {
                if (_key!=null) { SmartDashboard.putBoolean(_key, true); }
                return true;
            } else if (oi.isNoPressed()) {
                if (_key!=null) { SmartDashboard.putBoolean(_key, false); }
                return true;
            }
        }
        return false;
    }

    @Override
    protected void end() {
        ledStrip.clearOverride();
    }
}
