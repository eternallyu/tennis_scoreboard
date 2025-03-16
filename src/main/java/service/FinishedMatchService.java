package service;

import dao.MatchDao;
import dto.FinishedMatchDto;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mapper.MatchMapper;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class FinishedMatchService {

    public static final FinishedMatchService FINISHED_MATCH_SERVICE = new FinishedMatchService();
    public static final int PAGE_SIZE = 5;

    private final MatchDao matchDao = MatchDao.MATCH_DAO;
    private final MatchMapper matchMapper = MatchMapper.MATCH_MAPPER;

    public List<FinishedMatchDto> getFinishedMatches(int currentPageNumber, String filterByPlayerName) {
        int offset = (currentPageNumber - 1) * PAGE_SIZE;

        return filterByPlayerName == null ?
                matchDao.findAllPaginated(offset, PAGE_SIZE).stream().map(matchMapper::matchToFinishedMatchDto).toList() :
                matchDao.findFilteredByPlayerNamePaginated(offset, PAGE_SIZE, filterByPlayerName).stream().map(matchMapper::matchToFinishedMatchDto).toList();
    }

    public int getTotalPages(String filterByPlayerName) {
        int totalMatches = filterByPlayerName == null ?
                matchDao.findAllPaginated(0, matchDao.findAll().size()).size() :
                matchDao.findFilteredByPlayerNamePaginated(0, matchDao.findAll().size(), filterByPlayerName).size();
        return (int) Math.ceil((double) totalMatches / PAGE_SIZE);
    }
}
