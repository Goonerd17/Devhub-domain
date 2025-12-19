package goonerd.devhub.adapters.out.project.project;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import goonerd.devhub.adapters.in.project.command.SearchProjectCommand;
import goonerd.devhub.domain.project.ProjectProgressType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

import static goonerd.devhub.adapters.out.project.project.QProjectEntity.projectEntity;


@Repository
@RequiredArgsConstructor
public class ProjectQueryRepositoryImpl implements ProjectQueryRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ProjectEntity> search(SearchProjectCommand searchProjectCommand, Pageable pageable){
        List<ProjectEntity> content = queryFactory
                .selectFrom(projectEntity)
                .where(
                        keywordContains(searchProjectCommand.getKeyword()),
                        startDateBetween(
                                searchProjectCommand.getStartDateFrom(),
                                searchProjectCommand.getStartDateTo()
                        ),
                        endDateBetween(
                                searchProjectCommand.getEndDateFrom(),
                                searchProjectCommand.getEndDateTo()
                        ),
                        hasProgressType(searchProjectCommand.getProjectProgressType())
                )
                .orderBy(projectEntity.createdAt.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long total = queryFactory
                .select(projectEntity.count())
                .from(projectEntity)
                .where(
                        keywordContains(searchProjectCommand.getKeyword()),
                        startDateBetween(searchProjectCommand.getStartDateFrom(), searchProjectCommand.getStartDateTo()),
                        endDateBetween(searchProjectCommand.getEndDateFrom(), searchProjectCommand.getEndDateTo()),
                        hasProgressType(searchProjectCommand.getProjectProgressType())
                )
                .fetchOne();

        return new PageImpl<>(
                content,
                pageable,
                total == null ? 0 : total
        );
    }

    private BooleanExpression keywordContains(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return null;
        }
        QProjectEntity project = projectEntity;
        return project.title.containsIgnoreCase(keyword)
                .or(project.description.containsIgnoreCase(keyword));
    }

    private BooleanExpression startDateBetween(
            LocalDate from, LocalDate to) {

        QProjectEntity project = projectEntity;

        if (from != null && to != null) {
            return project.startDate.between(from, to);
        }
        if (from != null) {
            return project.startDate.goe(from);
        }
        if (to != null) {
            return project.startDate.loe(to);
        }
        return null;
    }

    private BooleanExpression endDateBetween(
            LocalDate from, LocalDate to) {

        QProjectEntity project = projectEntity;

        if (from != null && to != null) {
            return project.endDate.between(from, to);
        }
        if (from != null) {
            return project.endDate.goe(from);
        }
        if (to != null) {
            return project.endDate.loe(to);
        }
        return null;
    }

    private BooleanExpression hasProgressType(ProjectProgressType type) {
        if (type == null) {
            return null;
        }
        return projectEntity.projectProgressType.eq(type);
    }
}
