package br.com.cursomc.services.amazon;

import java.nio.file.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;

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
	 * @param localFilePath Caminho do arquivo
	 */
	public void uploadFile(final Path localFilePath) {
		log.info("==== Enviando arquivo ====");

		try {
			s3Client.putObject(
					new PutObjectRequest(bucketName, localFilePath.getFileName().toString(), localFilePath.toFile()));
		} catch (final AmazonServiceException e) {
			log.error("==== AmazonServiceException " + e.getLocalizedMessage());
			log.error("==== Error Code " + e.getErrorCode());
		} catch (final AmazonClientException e) {
			log.error("==== AmazonClientException " + e.getLocalizedMessage());
		}

		log.info("==== Arquivo enviado ====");

	}

}
