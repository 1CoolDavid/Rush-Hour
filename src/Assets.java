import java.awt.image.BufferedImage;

public class Assets 
{
 public static BufferedImage hredcar, tile, hyellowtruck, hpurplecar, 
                                             vbluetruck, vgreencar;
 public static void init()
 {
	 hredcar = ImageLoader.loadImage("/textures/hredcar.jpg");
	 hyellowtruck = ImageLoader.loadImage("/textures/hyellotruck.jpg");
	 hpurplecar = ImageLoader.loadImage("/textures/hpurplecar.jpg");
	 vbluetruck = ImageLoader.loadImage("/textures/vbluetruck.jpg");
	 vgreencar = ImageLoader.loadImage("/textures/vgreencar.jpg");
	 tile = ImageLoader.loadImage("/textures/tile.png");
 }
}
