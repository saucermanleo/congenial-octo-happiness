package com.example.pojo;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

public class ImageCode {
	
	private BufferedImage image;
	private String code;
	private LocalDateTime expireTime;
	
	@SuppressWarnings("static-access")
	public ImageCode(BufferedImage image, String code, int expireTime) {
		this.image = image;
		this.code = code;
		this.expireTime = this.expireTime.now().plusSeconds(expireTime);
	}
	public boolean isExpire() {
		return LocalDateTime.now().isAfter(expireTime);
	}
	
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public LocalDateTime getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(LocalDateTime expireTime) {
		this.expireTime = expireTime;
	}
	
}
