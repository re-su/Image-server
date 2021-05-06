package rotate;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageController {
	private ImageUtil imageUtil;
	
	public ImageController(InputStream input) throws IOException{
		BufferedImage imageBuffer = ImageIO.read(input);
		this.imageUtil = new ImageUtil(imageBuffer);
	}
	
	public int rotateImage() {
		int imageResultCode = imageUtil.checkPixels();
		
	    if(imageResultCode == 1) {
	    	imageUtil = imageUtil.rotate90ToRight();
	    } else if(imageResultCode == 2) {
	    	imageUtil = imageUtil.rotate90ToLeft();
	    } else if(imageResultCode == 8) {
	    	imageUtil = imageUtil.rotate180();
	    }
	    
	    return imageResultCode;
	}
	
	
	public ImageUtil getImageUtil() {
		return this.imageUtil;
	}
}
