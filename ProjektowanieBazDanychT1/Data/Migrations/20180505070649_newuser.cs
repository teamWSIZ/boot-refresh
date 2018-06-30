using Microsoft.EntityFrameworkCore.Migrations;
using System;
using System.Collections.Generic;

namespace ProjektowanieBazDanychT1.Data.Migrations
{
    public partial class newuser : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Exams_Users_UserId",
                table: "Exams");

            migrationBuilder.RenameColumn(
                name: "UserId",
                table: "Exams",
                newName: "ExamUserId");

            migrationBuilder.RenameIndex(
                name: "IX_Exams_UserId",
                table: "Exams",
                newName: "IX_Exams_ExamUserId");

            migrationBuilder.AddForeignKey(
                name: "FK_Exams_Users_ExamUserId",
                table: "Exams",
                column: "ExamUserId",
                principalTable: "Users",
                principalColumn: "Id",
                onDelete: ReferentialAction.Restrict);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Exams_Users_ExamUserId",
                table: "Exams");

            migrationBuilder.RenameColumn(
                name: "ExamUserId",
                table: "Exams",
                newName: "UserId");

            migrationBuilder.RenameIndex(
                name: "IX_Exams_ExamUserId",
                table: "Exams",
                newName: "IX_Exams_UserId");

            migrationBuilder.AddForeignKey(
                name: "FK_Exams_Users_UserId",
                table: "Exams",
                column: "UserId",
                principalTable: "Users",
                principalColumn: "Id",
                onDelete: ReferentialAction.Restrict);
        }
    }
}
