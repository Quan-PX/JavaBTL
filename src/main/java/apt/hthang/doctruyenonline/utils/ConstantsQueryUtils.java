package apt.hthang.doctruyenonline.utils;

/**
 * @author Huy Thang
 */

public class ConstantsQueryUtils {

    public static final String STORY_NEW_UPDATE_BY_CATEGORY = "SELECT s.id, s.vnName, s.images, s.author, s.updateDate,"
            + " c.id as chapterId, c.chapterNumber,"
            + " u.displayName, u.username, s.dealStatus"
            + " FROM Story s LEFT JOIN (SELECT c.* FROM Chapter c INNER JOIN"
            + " (SELECT MAX(c.id) AS maxChapterID FROM Story s"
            + " LEFT JOIN Chapter c"
            + " ON s.id = c.storyId GROUP BY s.id) d"
            + " ON c.id = d.maxChapterID "
            + " WHERE c.status IN :chapterStatus) c "
            + " ON s.id = c.storyID "
            + " LEFT JOIN user u on c.userPosted = u.id"
            + " LEFT JOIN `story_category` sc on s.id = sc.storyId"
            + " WHERE  sc.categoryId = :categoryId AND s.status IN :storyStatus"
            + " GROUP BY s.id"
            + " ORDER BY s.updateDate DESC";

    public static final String COUNT_STORY_NEW_UPDATE_BY_CATEGORY = "SELECT COUNT(*)"
            + " FROM Story s LEFT JOIN (SELECT c.* FROM Chapter c INNER JOIN"
            + " (SELECT MAX(c.id) AS maxChapterID FROM Story s"
            + " LEFT JOIN Chapter c"
            + " ON s.id = c.storyId GROUP BY s.id) d"
            + " ON c.id = d.maxChapterID "
            + " WHERE c.status IN :chapterStatus) c "
            + " ON s.id = c.storyID "
            + " LEFT JOIN user u on c.userPosted = u.id"
            + " LEFT JOIN `story_category` sc on s.id = sc.storyId"
            + " WHERE  sc.categoryId = :categoryId AND s.status IN :storyStatus"
            + " GROUP BY s.id"
            + " ORDER BY s.updateDate DESC";
    
}