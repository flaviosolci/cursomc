package br.com.cursomc.services.amazon;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

import lombok.extern.slf4j.Slf4j;

/**
 * Serviço para acessar o S3 da Amazon
 *
 * @author Flavio Solci
 *
 */
@Service
@Slf4j
public class S3Service {

	/** Acesso ao amazon S3. See {@link br.com.cursomc.config.S3Config} */
	@Autowired
	private AmazonS3 s3Client;

	/** Nome do bucket. application.properties */
	@Value("${s3.bucket}")
	private String bucketName;

	/**
	 * Envia um arquivo para o S3
	 *
	 * @param multipartFile Arquivo
	 */
	public URI uploadFile(final MultipartFile multipartFile) {
		log.info("==== Enviando arquivo ====");

		try (InputStream is = multipartFile.getInputStream()) {
			final String fileName = multipartFile.getOriginalFilename();
			final String contentType = multipartFile.getContentType();

			final URI uploadFile = uploadFile(is, fileName, contentType);
			log.info("==== Arquivo enviado " + uploadFile);
			return uploadFile;
		} catch (final AmazonServiceException e) {
			log.error("==== AmazonServiceException " + e.getLocalizedMessage());
			log.error("==== Error Code " + e.getErrorCode());
			throw new RuntimeException(e);
		} catch (final AmazonClientException e) {
			log.error("==== AmazonClientException " + e.getLocalizedMessage());
			throw new RuntimeException(e);
		} catch (final IOException e) {
			log.error("==== IOException " + e.getLocalizedMessage());
			throw new RuntimeException(e);
		}

	}

	public URI uploadFile(final InputStream inputStream, final String fileName, final String contentType) {
		try {
			final ObjectMetadata meta = new ObjectMetadata();
			meta.setContentType(contentType);
			s3Client.putObject(bucketName, fileName, inputStream, meta);

			return s3Client.getUrl(bucketName, fileName).toURI();
		} catch (final URISyntaxException e) {
			log.error("==== URISyntaxException " + e.getLocalizedMessage());
			throw new RuntimeException(e);
		}
	}

}
