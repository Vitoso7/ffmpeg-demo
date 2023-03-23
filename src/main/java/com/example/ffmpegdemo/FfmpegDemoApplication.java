package com.example.ffmpegdemo;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;

// FFMPEG WRAPPERS ->

@SpringBootApplication
public class FfmpegDemoApplication {
    public static void main(String[] args) throws IOException, InterruptedException {
        String fileTest = "/Users/victorlisboa/personal-stuff/fuck-java/ffmpeg-demo/videos/ARQUIVO_COMERCIAL_TESTE_ORIGINAL_8CH.mxf";

        try {
            ProcessBuilder pb = new ProcessBuilder(
                    "ffmpeg", "-hide_banner", "-i", fileTest,
                    "-vf", "blackdetect", "-f", "null", "-");

            pb.redirectErrorStream(true);
            Process p = pb.start();

            InputStream is = p.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains("black_start:") || line.contains("black_end:")) {
                    System.out.println(line);
                }
            }
            p.waitFor();
            p.destroy();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
