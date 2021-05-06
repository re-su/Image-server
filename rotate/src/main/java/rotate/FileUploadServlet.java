package rotate;

import java.io.IOException;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

public class FileUploadServlet extends HttpServlet {
	static final long serialVersionUID = 1L;
	  public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    Part filePart = null;
		    int rotateResultCode = 0;

		    for(Part part : request.getParts()) {
		    	if(part != null && part.getContentType() != null && part.getContentType().equals("image/png")) {
		    		filePart = part;
		    	}
		    }
		    
		    if(filePart == null) {
		    	response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE, "Image is not in .png format, or you have not provided name(key) for file");
		    } else {
			    ImageController imageController = new ImageController( filePart.getInputStream() );
			    rotateResultCode = imageController.rotateImage();
			    
			    if(rotateResultCode == 0) {
			    	response.sendError(HttpServletResponse.SC_NO_CONTENT);
			    } else if(rotateResultCode == 1 || 
			    		  rotateResultCode == 2 || 
			    	 	  rotateResultCode == 4 || 
			    		  rotateResultCode == 8) {
				    response.setContentType("image/png");
				    OutputStream out = response.getOutputStream();
				    ImageIO.write(imageController.getImageUtil().getBufferedImage(), "png", out);
				    out.close();	
			    } else {
			    	response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			    }
		    }
	}
}
