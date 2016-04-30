package coinmachine;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;


/**
 * A user interface that can be use to insert 1 baht, 5 baht, and 10 baht coin.
 * It also tells the amount of money you inserted and how many coin have been
 * inserted so far.
 * @author kaniup
 *
 */
public class CoinMachineUI extends JFrame implements Observer {
	
	JPanel panel;
	CoinMachine coinMachine;
	JLabel balanceLabel;
	JLabel capacityStatusLabel;
	JProgressBar capacityBar;
	JButton oneBahtCoin;
	JButton fiveBahtCoin;
	JButton tenBahtCoin;
	
	
	
	/**
	 * Constructor for CoinMachineUI
	 * @param coinMachine the coin machine that will be use with this user interface
	 */
	public CoinMachineUI(CoinMachine coinMachine) {
		this.coinMachine = coinMachine;
		initComponent();
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	
	/**
	 * Use for initializing the component of the UI
	 */
	private void initComponent(){
		String balanceText = String.format("Balance: %d", coinMachine.getBalance());
		
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		Container con1 = new Container();
		Container con2 = new Container();
		capacityBar = new JProgressBar();
		balanceLabel = new JLabel(balanceText);
		capacityStatusLabel = new JLabel("Status :");
		oneBahtCoin = new JButton();
		oneBahtCoin.setIcon(new ImageIcon(CoinMachineUI.class.getResource("/images/1baht.png")));
		fiveBahtCoin = new JButton();
		fiveBahtCoin.setIcon(new ImageIcon(CoinMachineUI.class.getResource("/images/5baht.png")));
		tenBahtCoin = new JButton();
		tenBahtCoin.setIcon(new ImageIcon(CoinMachineUI.class.getResource("/images/10baht.png")));
		
		this.add(panel);
		panel.add(con1);
		panel.add(con2);
		
		con1.setLayout(new FlowLayout());
		con2.setLayout(new FlowLayout());
		
		capacityBar.setMinimum(0);
		capacityBar.setMaximum(coinMachine.getCapacity());
		capacityBar.setStringPainted(true);
		
		con1.add(balanceLabel);
		con1.add(capacityStatusLabel);
		con1.add(capacityBar);
		
		con2.add(oneBahtCoin);
		con2.add(fiveBahtCoin);
		con2.add(tenBahtCoin);
		
		OneBahtActionListener insertOne = new OneBahtActionListener();
		FiveBahtActionListener insertFive = new FiveBahtActionListener();
		TenBahtActionListener insertTen = new TenBahtActionListener();
		
		oneBahtCoin.addActionListener(insertOne);
		fiveBahtCoin.addActionListener(insertFive);
		tenBahtCoin.addActionListener(insertTen);
		
		this.pack();
	}
	
	/**
	 * ActionListener for the one baht button, makes the button able to insert coin when pressed
	 * @author kaniup
	 *
	 */
	class OneBahtActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Coin oneBaht = new Coin(1, "Baht");
			System.out.println("Insert one baht");
			coinMachine.insert(oneBaht);
		}
	}
	
	/**
	 * ActionListener for the five baht button, makes the button able to insert coin when pressed
	 * @author kaniup
	 *
	 */
	class FiveBahtActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			Coin fiveBaht = new Coin(5, "Baht");
			System.out.println("Insert five baht");
			coinMachine.insert(fiveBaht);
			
		}
	}

	/**
	 * ActionListener for the ten baht button, makes the button able to insert coin when pressed
	 * @author kaniup
	 *
	 */
	class TenBahtActionListener implements ActionListener{	
		@Override
		public void actionPerformed(ActionEvent e) {
			Coin tenBaht = new Coin(10, "Baht");
			if(!coinMachine.isFull())System.out.println("Insert ten baht");
			coinMachine.insert(tenBaht);	
			
		}
	}
	
	/**
	 * 
	 * Use to doing something after the observer(CoinMachineUI) has been notify
	 * that the CoinMachine has change it's state.
	 * 
	 */
	@Override
	public void update(Observable o, Object arg) {
		String text = String.format("Balance : %d", coinMachine.getBalance());
		balanceLabel.setText(text);
		capacityBar.setString(Integer.toString(coinMachine.getCount()));
		capacityBar.setValue(coinMachine.getCount());	
	}
}
