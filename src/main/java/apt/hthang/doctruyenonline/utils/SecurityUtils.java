package apt.hthang.doctruyenonline.utils;

public class SecurityUtils {
    public static final String[] PERMIT_ALL_LINK = {"/",
            "/dang-nhap",
            "/logout",
            "/danh-muc*",
            "/the-loai*", "/truyen*"};
    
    public static final String[] ROLE_USER_LINK = {"/tai-khoan",
            "/tai-khoan/theo_doi",
            "/tai-khoan/doi_mat_khau",
            "/tai-khoan/nap_dau",
            "/tai-khoan/giao_dich",
            "/tai-khoan/quan_ly_truyen",
            "/tai-khoan/them_truyen",
            "/tai-khoan/list_chuong/",
            "/tai-khoan/them_chuong*",
    };
    
    public static final String[] ROLE_CONANDMOD_LINK = {"/tai-khoan/rut_tien"};
    
    public static final String[] ROLE_ADMIN_MOD_LINK = {"/quan-tri",
            "/quan-tri/list_user",
            "/quan-tri/the_loai",
            "/quan-tri/them_the_loai"};
}
