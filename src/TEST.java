import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.util.Base64;

public class TEST {

	public static String base64 = "data:image/jpeg;base64,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";

	public static void main(String args[]) throws IOException {

		try {

	        // エンコードは、Base64.getEncoder() で得られるオブジェクトを利用する
	        // Base64 エンコード結果を String としたい場合は、Encoder#encodeToString() を呼び出す
	        String encoded = Base64.getEncoder()
	                .encodeToString(base64.getBytes());
	        
	        Blob blob= ;

	        // デコードは Base64.getDecoder() で得られるオブジェクトを利用する
	        String decoded = new String(Base64.getDecoder()
	                .decode(encoded));

	        System.out.println("# Base64.getEncoder() / Base64.getDecoder() のデモ");
	        System.out.println("エンコード結果 : " + encoded);
	        System.out.println("デコード結果 : " + decoded);
	        System.out.println();


			FileInputStream in = new FileInputStream(base64);

			ByteArrayOutputStream bo = new ByteArrayOutputStream();

			byte[] b = new byte[1024];
			int len;

			while ((len = in.read(b, 0, b.length)) > 0) {
				bo.write(b, 0, len);
			}
			//BufferedImage bimage = toBufferedImage(Base64.getDecoder().decode(bo.toString()));

			int a=0;
			a=1;

			byte[] encode =Base64.getEncoder().encode(b);
			String test = new String(encode);
			//画像をバイナリ変換
			 //byte[] imageBinary = Base64.getDecoder().decode(src);

			int aa=0;
			aa=1;
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

//	public static BufferedImage toBufferedImage(byte[] imageBinary) throws IOException {
//		BufferedImage img = ImageIO.read(new ByteArrayInputStream(imageBinary));
//		int width = img.getWidth();
//		int height = img.getHeight();
//		BufferedImage bufImage = new BufferedImage(img.getWidth(), height, BufferedImage.TYPE_INT_RGB);
//		for (int y = 0; y < height; y++) {
//			for (int x = 0; x < width; x++) {
//				int c = img.getRGB(x, y);
//				int r = c >> 16 & 0xff;
//				int g = c >> 8 & 0xff;
//				int b = c & 0xff;
//				int rgb = 0xff000000 | r << 16 | g << 8 | b;
//				bufImage.setRGB(x, y, rgb);
//			}
//		}
//		return bufImage;
//	}
}
