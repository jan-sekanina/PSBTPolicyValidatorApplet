package main;

import applet.MainApplet;
import com.licel.jcardsim.smartcardio.CardSimulator;
import com.licel.jcardsim.utils.AIDUtil;
import javacard.framework.AID;

import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;
import java.util.Scanner;

public class Run {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a PSBT transaction: " + System.lineSeparator());
        String psbt = TransactionsImported.validTransaction8; //sc.nextLine();
        System.out.print("You entered a following transaction:" + System.lineSeparator());
        System.out.print(psbt + System.lineSeparator());
        System.out.print(System.lineSeparator());

        // 1. create simulator
        CardSimulator simulator = new CardSimulator();

        // 2. install applet
        AID appletAID = AIDUtil.create("F000000001");
        simulator.installApplet(appletAID, MainApplet.class);

        // 3. select applet
        simulator.selectApplet(appletAID);

        // 4. send APDU
        AppletControl ap = new AppletControl(simulator, psbt);
        ap.UploadTransaction();
        ap.DownloadDebugV0();
    }

}