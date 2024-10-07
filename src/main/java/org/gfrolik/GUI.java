package org.gfrolik;

import javax.swing.JOptionPane;
import java.net.URL;

public class GUI {

    // Zeigt das Bild und fordert den Benutzer zur Eingabe auf
    public String showImageWithPrompt(URL imageUrl, String statistics) {
        // Hier könntest du die URL als Bild in einer JOptionPane anzeigen.
        // Für die Einfachheit gehen wir davon aus, dass die URL als Text gezeigt wird.
        return JOptionPane.showInputDialog(
                null,
                "Statistik: " + statistics + "\nBild: " + imageUrl + "\nGib das Wort ein:"
        );
    }

    // Zeigt eine einfache Nachricht (z.B. richtig/falsch) an
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}

