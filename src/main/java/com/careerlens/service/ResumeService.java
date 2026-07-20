package com.careerlens.service;

import com.careerlens.entity.Resume;
import com.careerlens.entity.User;
import com.careerlens.repository.ResumeRepository;
import com.careerlens.repository.UserRepository;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final UserRepository userRepository;

    public ResumeService(ResumeRepository resumeRepository,
                         UserRepository userRepository) {
        this.resumeRepository = resumeRepository;
        this.userRepository = userRepository;
    }

    public Resume uploadResume(MultipartFile file, Long userId) throws IOException {

        if (file.isEmpty()) {
            throw new RuntimeException("Please upload a valid resume.");
        }

        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found.");
        }

        User user = optionalUser.get();

        try (PDDocument document = Loader.loadPDF(file.getBytes())) {

            PDFTextStripper stripper = new PDFTextStripper();

            String resumeText = stripper.getText(document);

            Resume resume = Resume.builder()
                    .fileName(file.getOriginalFilename())
                    .resumeText(resumeText)
                    .uploadDate(LocalDateTime.now())
                    .user(user)
                    .build();

            return resumeRepository.save(resume);
        }
    }
}