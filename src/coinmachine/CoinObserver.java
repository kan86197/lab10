package coinMachineLab;

import java.awt.Container;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

/**
 * A user interface that shows the amount of coin that has been inserted into the machine
 * @author kaniup
 *
 */
public class CoinObserver extends JFrame implements Observer {
	
	JPanel panel = new JPanel();
	JLabel coinTag;
	JTextField resultField;
	JLabel acceptingCoin;
	CoinMachine coinMachine;
	
	
	
	/**
	 * Constructor for CoinObserver
	 * @param coinMachine the coin machine that will be use with this user interface
	 */
	public CoinObserver(CoinMachine coinMachine){
		this.coinMachine = coinMachine;
		setVisible(true);
		initComponent();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/**
	 * Use to initialize the component for this user interface
	 */
	private void initComponent(){
		coinTag = new JLabel("#Coins");
		resultField = new JTextField(10);
		acceptingCoin = new JLabel("Accepting Coins");
		
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		
		resultField.setEditable(false);
		
		Container con1 = new Container();
		con1.setLayout(new FlowLayout());
		con1.add(coinTag);
		con1.add(resultField);
		
		Container con2 = new Container();
		con2.setLayout(new FlowLayout());
		con2.add(acceptingCoin);
		
		this.add(panel);
		panel.add(con1);
		panel.add(con2);
		
		this.pack();
	}
	
	/**
	 * The action to be execute when the CoinObserver is notified that the state
	 * of the CoinMachine has been changed
	 */
	@Override
	public void update(Observable obs, Object arg1) {
		resultField.setText(Integer.toString(coinMachine.getCount()));
	}

}
