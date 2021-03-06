package com.javaspring.team2.project.smdb.service;

import com.javaspring.team2.project.smdb.domain.ContributionRole;
import com.javaspring.team2.project.smdb.domain.Genre;
import com.javaspring.team2.project.smdb.domain.Person;
import com.javaspring.team2.project.smdb.domain.Title;
import com.javaspring.team2.project.smdb.repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class TitleServiceImpl extends BaseServiceImpl<Title> implements TitleService {
    private final TitleRepository titleRepository;

    @Override
    JpaRepository<Title, Long> getRepository() {
        return titleRepository;
    }

    @Override
   public  Title findTitleByPrimaryTitle(String primaryTitle){
        return titleRepository.findTitleByPrimaryTitle(primaryTitle);
    }

    @Override
    public Boolean existsByPrimaryTitle(String primaryTitle){
        return titleRepository.existsByPrimaryTitle(primaryTitle);
    }

    @Override
    public List<Person> getPeopleParticipatingInTitle(String primaryTitle) {
        return titleRepository.getPeopleParticipatingInTitle(primaryTitle);
    }

    @Override
    public void csvTitlesExport(Writer writer) throws IOException {
        List<Title> titles = titleRepository.findAll();
        CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
        csvPrinter.printRecord("title_id", "primaryTitle", "Duration", "SmdbRating", "ReleaseYear", "Genres", "CountrisOfOrigin", "Languages", "StoryLine");
        for (Title t : titles) {
            csvPrinter.printRecord(t.getId(), t.getPrimaryTitle(), t.getDurationInMinutes(), t.getSmdbRating(), t.getReleaseYear(),
                    t.getGenres(), t.getCountriesOfOrigin(), t.getLanguages(), t.getStoryLine());
        }
        logger.info("[Titles] table exported successfully. Number of rows {}", titles.size());
    }
}
