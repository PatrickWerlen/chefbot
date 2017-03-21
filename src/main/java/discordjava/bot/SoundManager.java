package discordjava.bot;

import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.IMessage;
import sx.blah.discord.util.audio.AudioPlayer;
import sx.blah.discord.util.audio.providers.AudioInputStreamProvider;
import sx.blah.discord.util.audio.providers.FileProvider;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class SoundManager {
    private List<AudioPlayer.Track> playlist = new CopyOnWriteArrayList<AudioPlayer.Track>();
    private IMessage message;

    public SoundManager(String path, IMessage message, boolean fromFile){
        this.message = message;
        if(fromFile) {
            getPlaylist(path);
        }
        playSound(path, this.message.getGuild(), fromFile);
    }

    public void playSound(String path, IGuild guild, boolean fromFile) {
        try {
            playAudioFromUrl("nanana", guild);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        }

        /*if(fromFile){
            try {
                playAudioFromFile(guild);
            }catch (Exception e){
                e.printStackTrace();
            }


        }/*else{
            try {
                playAudioFromUrl(path, guild);
            }catch (Exception e){
                e.printStackTrace();
            }
        }*/
    }


    public List<AudioPlayer.Track> getPlaylist(String path){
        //gets all the file in this folder
        File directory = new File(path);
        File[] fList = directory.listFiles();
        //convert them into Tracks and add them to list
        for(File file : fList){
            try {
                playlist.add(new AudioPlayer.Track(new FileProvider(file)));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return playlist;
    }

    // Queue audio from file (quality hardly depens on connection to discord -> njäh)
    private void playAudioFromFile(IGuild guild) throws IOException, UnsupportedAudioFileException {
        AudioPlayer player = AudioPlayer.getAudioPlayerForGuild(guild); // Get AudioPlayer for guild;
        player.queue(playlist.get(0)); // Queue file
    }

    // Queue audio from specified URL stream for guild
    private static void playAudioFromUrl(String s_url, IGuild guild) throws IOException, UnsupportedAudioFileException {
        URL url = new URL("http://http-live.sr.se/p1-mp3-192");
        AudioInputStream stream = AudioSystem.getAudioInputStream(url);
        AudioPlayer.Track track = new AudioPlayer.Track(stream);
        AudioPlayer player = AudioPlayer.getAudioPlayerForGuild(guild);// Get AudioPlayer for guild;
        player.queue(track);
    }

}
