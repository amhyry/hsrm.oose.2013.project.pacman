package de.hsrm.cs.oose13.basics;
import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
/**
 * This Sound Class creates an Sound Object of a wav-File.
 * @author      Arnold Riemer <arnold.riemer@gmail.com>
 * @version     1.0                
 * @since       2014-01-26          
 */


public class Sound {
    private Clip clip;
    private DataLine.Info info;
    private AudioFormat af;
    private byte[] audio;
    private int size;
    
    /**
     * Constructs a Sound Object out of a File Path.<p>
     * @param path Path of a wav-File as String.      
     */
    public Sound(String path){
	   try{
	        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(path));
	        af     = audioInputStream.getFormat();
	        size      = (int) (af.getFrameSize() * audioInputStream.getFrameLength());
	        audio       = new byte[size];
	        info      = new DataLine.Info(Clip.class, af, size);
	        audioInputStream.read(audio, 0, size);
	    }catch(Exception e){ e.printStackTrace(); }	
    }
    /**
     * Plays Soundobject one time.        
     */ 
    public void start(){
    	try {
    		clip = (Clip) AudioSystem.getLine(info);
			clip.open(af, audio, 0, size);
			clip.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
    /**
     * Plays Soundobject n-times in a row.
     * @param times How much times played in a row as integer.
     */ 
    
    public void loop(int times){
    	try {
    		clip = (Clip) AudioSystem.getLine(info);
			clip.open(af, audio, 0, size);
			clip.loop(times);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    } 
    
    /**
     * Stops playing Soundobject.         
     */
    
    public void stop(){
    	if(clip != null) {
    		clip.stop(); 
    	}
    }
}