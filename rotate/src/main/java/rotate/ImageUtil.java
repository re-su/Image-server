package rotate;

import java.awt.image.BufferedImage;

public class ImageUtil {
	private BufferedImage image;
	private int width;
	private int height;
	
	public ImageUtil(BufferedImage image) {
		this.image = image;
	    this.width = this.image.getWidth();
	    this.height = this.image.getHeight();
	}
	/*
	 * Result Codes
	 * 
	 * 0 - There is no desired combination
	 * 1 - Horizontally, white pixels are first
	 * 2 - Horizontally, red pixels are first
	 * 4 - Vertically, white pixels are first
	 * 8 - Vertically, red pixels are first
	 * Different - Multiple combinations
	 */
	public int checkPixels() {
		int resultCode = 0;
		
		boolean verticalWhiteFirst = false, verticalRedFirst = false, horizontalWhiteFirst = false, horizontalRedFirst = false;
		
		if(this.width >= 6) {
		    for (int row = 0; row < this.height; row++) {
			       for (int col = 0; col < this.width - 5; col++) {        
				          if(Integer.toHexString(this.image.getRGB(col, row)).equals("ffffffff") &&
				        	 Integer.toHexString(this.image.getRGB(col + 1, row)).equals("ffffffff") &&
				        	 Integer.toHexString(this.image.getRGB(col + 2, row)).equals("ffffffff") &&
				        	 Integer.toHexString(this.image.getRGB(col + 3, row)).equals("ffff0000") &&
				        	 Integer.toHexString(this.image.getRGB(col + 4, row)).equals("ffff0000") &&
				        	 Integer.toHexString(this.image.getRGB(col + 5, row)).equals("ffff0000")) {
				        	 if(!verticalWhiteFirst) resultCode += 1;
				        	 verticalWhiteFirst = true;
				          }
				          else if(Integer.toHexString(this.image.getRGB(col, row)).equals("ffff0000") &&
						          Integer.toHexString(this.image.getRGB(col + 1, row)).equals("ffff0000") &&
						          Integer.toHexString(this.image.getRGB(col + 2, row)).equals("ffff0000") &&
						          Integer.toHexString(this.image.getRGB(col + 3, row)).equals("ffffffff") &&
						          Integer.toHexString(this.image.getRGB(col + 4, row)).equals("ffffffff") &&
						          Integer.toHexString(this.image.getRGB(col + 5, row)).equals("ffffffff")) {
				        	  if(!verticalRedFirst) resultCode += 2;
				        	  verticalRedFirst = true;
				          }
			       }
			}
		}
		
		if(this.height >= 6) {
		    for (int col = 0; col < this.width; col++) {
			       for (int row = 0; row < this.height - 5; row++) {        
				          if(Integer.toHexString(this.image.getRGB(col, row)).equals("ffffffff") &&
				        	 Integer.toHexString(this.image.getRGB(col, row + 1)).equals("ffffffff") &&
				        	 Integer.toHexString(this.image.getRGB(col, row + 2)).equals("ffffffff") &&
				        	 Integer.toHexString(this.image.getRGB(col, row + 3)).equals("ffff0000") &&
				        	 Integer.toHexString(this.image.getRGB(col, row + 4)).equals("ffff0000") &&
				        	 Integer.toHexString(this.image.getRGB(col, row + 5)).equals("ffff0000")) {
				        	  if(!horizontalWhiteFirst) resultCode += 4;
				        	  horizontalWhiteFirst = true;
				          }
				          else if(Integer.toHexString(this.image.getRGB(col, row)).equals("ffff0000") &&
						          Integer.toHexString(this.image.getRGB(col, row + 1)).equals("ffff0000") &&
						          Integer.toHexString(this.image.getRGB(col, row + 2)).equals("ffff0000") &&
						          Integer.toHexString(this.image.getRGB(col, row + 3)).equals("ffffffff") &&
						          Integer.toHexString(this.image.getRGB(col, row + 4)).equals("ffffffff") &&
						          Integer.toHexString(this.image.getRGB(col, row + 5)).equals("ffffffff")) {
				        	  if(!horizontalRedFirst) resultCode += 8;
				        	  horizontalRedFirst = true;
				          }
			       }
			}
		}
		
		return resultCode;
	}
	
	 public ImageUtil rotate90ToLeft(){
			int width = this.image.getWidth();
			int height = this.image.getHeight();
			BufferedImage returnImage = new BufferedImage( height, width , this.image.getType()  );

			for( int x = 0; x < width; x++ ) {
				for( int y = 0; y < height; y++ ) {
					returnImage.setRGB(y, width - x - 1, this.image.getRGB(x, y));

				}
			}
			
			return new ImageUtil(returnImage);
	}
	 
	 public ImageUtil rotate90ToRight(){
			int width = this.image.getWidth();
			int height = this.image.getHeight();
			
			BufferedImage returnImage = new BufferedImage( height, width , this.image.getType()  );

			for( int x = 0; x < width; x++ ) {
				for( int y = 0; y < height; y++ ) {
					returnImage.setRGB( height - y - 1, x, this.image.getRGB( x, y));
				}
			}
			
			return new ImageUtil(returnImage);
	 }
	
	public ImageUtil rotate180() {
			int width = this.image.getWidth();
			int height = this.image.getHeight();

			BufferedImage returnImage = new BufferedImage( width, height, this.image.getType()  );


			for( int x = 0; x < width; x++ ) {
				for( int y = 0; y < height; y++ ) {
					returnImage.setRGB(width - x - 1, height - y - 1, this.image.getRGB(x, y));
				}
			}

			return new ImageUtil( returnImage );

	}
	
	public BufferedImage getBufferedImage() {
		return this.image;
	}
}
