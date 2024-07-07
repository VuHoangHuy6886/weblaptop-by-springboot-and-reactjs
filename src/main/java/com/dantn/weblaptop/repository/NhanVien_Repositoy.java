package com.dantn.weblaptop.repository;

import com.dantn.weblaptop.constant.AccountStatus;
import com.dantn.weblaptop.entity.nhanvien.NhanVien;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NhanVien_Repositoy extends JpaRepository<NhanVien, Integer> {
    @Query("SELECT nv FROM NhanVien nv WHERE " +
            "(:search is null or nv.ma LIKE %:search% or nv.ten LIKE %:search% or nv.email like %:search% or nv.sdt like %:search%) " +
            "and (:trangThai is null or nv.trangThai = :trangThai)")
    Page<NhanVien> pageSearch(
            Pageable pageable
            , @Param("search") String search
    );

    @Query(value = "SELECT nv FROM NhanVien nv WHERE nv.trangThai = :trangThai")
    List<NhanVien> getNhanVienByTrangThai(AccountStatus trangThai);

    @Query(value = "SELECT nv FROM NhanVien nv WHERE nv.email = :email")
    NhanVien findByEmail(@Param("email") String email);

    @Query(value = "SELECT nv FROM NhanVien nv WHERE nv.cccd = :cccd")
    NhanVien findByCccd(@Param("cccd") String cccd);

    @Query(value = "UPDATE NhanVien nv SET nv.trangThai = :accountStatus WHERE nv.id = :id")
    void revertStatus(AccountStatus accountStatus, Long id);

    @Query(value = "UPDATE NhanVien nv SET nv.matKhau = :password WHERE nv.email = :email")
    void updatePasswordEmployee(String password, String email);

    @Query(value = "UPDATE NhanVien nv SET nv.hinhAnh = :image WHERE nv.email = :email")
    void updateImageEmployee(String image, String email);
}
