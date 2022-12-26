/*
Project Name: A Simple Midi Piano
Author: Zikang Zheng
Email: zikang.zheng@outlook.com
 */

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.sound.midi.*;
import javax.swing.*;


public class keyboard {
    // Initialize a new Midi channel and a new synthesizer for later use.
    MidiChannel channel;
    Synthesizer synths;

    {
        try {
            synths = MidiSystem.getSynthesizer(); // Make midi available at the beginning.
            synths.loadAllInstruments(synths.getDefaultSoundbank());
            Instrument[] insts = synths.getLoadedInstruments();
            MidiChannel[] channels = synths.getChannels();
            synths.open();
            channel = channels[0];
        } catch (MidiUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws MidiUnavailableException { // Main method to run the program.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                keyboard keyboard1 = new keyboard();
                try {
                    keyboard1.create_and_show_GUI();
                } catch (MidiUnavailableException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void create_and_show_GUI() throws MidiUnavailableException {
        JFrame frame = new JFrame("MIDI Piano");  // Window title.
        frame.setSize(1800, 300);  // Window size.
        JPanel panel = new JPanel(); // Main panel.
        panel.setLayout(new GridLayout(1, 0)); // Set the layout to 1 row and unlimited columns.
        // Initialize a new sequencer and open it for later use.
        Sequencer sequencer = MidiSystem.getSequencer(); // Get the default Sequencer
        sequencer.open(); // Open device

        //File Chooser button.
        JButton file_button = new JButton("▷");  // File button is used to load midi music file and play it.
        file_button.setBackground(Color.WHITE);
        // Add mouse action listener.
        file_button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFileChooser jfc = new JFileChooser();
                int val = jfc.showOpenDialog(null);
                if (val == JFileChooser.APPROVE_OPTION) {

                    File file = jfc.getSelectedFile();  // Get selected file and save it to 'file'.

                    Sequence sequence = null;
                    try {
                        sequence = MidiSystem.getSequence(file);
                        sequencer.setSequence(sequence); // load selected midi file into sequencer
                        System.out.println(sequence.getMicrosecondLength()); //Get the length of the song in microseconds.
                        sequencer.start();
                    } catch (InvalidMidiDataException invalidMidiDataException) {
                        System.err.println(invalidMidiDataException);
                    } catch (IOException ioException) {
                        System.err.println(ioException);
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        JButton stop_button = new JButton("❚❚"); // Stop button is used to stop the music.
        stop_button.setBackground(Color.WHITE);
        stop_button.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sequencer.stop();  // When clicked the stop button, the music will stop.
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        panel.add(file_button);  //Add the file chooser button to the panel.
        panel.add(stop_button);  //Add the stop button to the panel.


        int[] notes = new int[]{46, 47, 48, 49, 50, 52, 53, 54, 55, 56, 57, 58,
                60, 61, 62, 63, 64, 66, 67, 68, 69, 70, 71, 72, 74, 75, 76, 77,  // This array saves all the notes, including pitches. '60' is the middle C note.
                78, 80, 81, 82, 83, 84, 85, 86};
        ArrayList<Key> white_key_array = new ArrayList();
        ArrayList<Key> black_key_array = new ArrayList();
        for (int i = 0; i < notes.length; i++) {
            if (notes[i] % 2 == 0) {
                White_key new_key_white = new White_key(notes[i]);
                White_Add(new_key_white);
                white_key_array.add(new_key_white);
                panel.add(new_key_white);
            } else {
                Black_key new_key_black = new Black_key(notes[i]);
                Black_Add(new_key_black);
                black_key_array.add(new_key_black);
                panel.add(new_key_black);
            }
        }


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        //frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }

    public void Black_Add(Black_key kk) {
        kk.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                channel.noteOn(kk.note, 100);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                channel.noteOff(kk.note, 100);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });


    }

    public void White_Add(White_key kk) {
        kk.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {
                channel.noteOn(kk.note, 100);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                channel.noteOff(kk.note, 100);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });


    }

    interface Key_args {
        int width = 100;
        int height = 200;
    }

    class Key extends JButton {
        int note;
        public Key() {

        }
    }

    class White_key extends Key implements Key_args {
        public White_key(int note) {
            this.note = note;
            setBackground(Color.WHITE);
        }

    }

    class Black_key extends Key implements Key_args {
        int note;
        public Black_key(int note) {
            this.note = note;
            setBackground(Color.BLACK);
        }
    }


}