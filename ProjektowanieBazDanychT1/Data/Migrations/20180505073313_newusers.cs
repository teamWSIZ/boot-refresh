using Microsoft.EntityFrameworkCore.Migrations;
using System;
using System.Collections.Generic;

namespace ProjektowanieBazDanychT1.Data.Migrations
{
    public partial class newusers : Migration
    {
        protected override void Up(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Exams_Users_ExamUserId",
                table: "Exams");

            migrationBuilder.DropPrimaryKey(
                name: "PK_Users",
                table: "Users");

            migrationBuilder.RenameTable(
                name: "Users",
                newName: "ExamUsers");

            migrationBuilder.AddPrimaryKey(
                name: "PK_ExamUsers",
                table: "ExamUsers",
                column: "Id");

            migrationBuilder.AddForeignKey(
                name: "FK_Exams_ExamUsers_ExamUserId",
                table: "Exams",
                column: "ExamUserId",
                principalTable: "ExamUsers",
                principalColumn: "Id",
                onDelete: ReferentialAction.Restrict);
        }

        protected override void Down(MigrationBuilder migrationBuilder)
        {
            migrationBuilder.DropForeignKey(
                name: "FK_Exams_ExamUsers_ExamUserId",
                table: "Exams");

            migrationBuilder.DropPrimaryKey(
                name: "PK_ExamUsers",
                table: "ExamUsers");

            migrationBuilder.RenameTable(
                name: "ExamUsers",
                newName: "Users");

            migrationBuilder.AddPrimaryKey(
                name: "PK_Users",
                table: "Users",
                column: "Id");

            migrationBuilder.AddForeignKey(
                name: "FK_Exams_Users_ExamUserId",
                table: "Exams",
                column: "ExamUserId",
                principalTable: "Users",
                principalColumn: "Id",
                onDelete: ReferentialAction.Restrict);
        }
    }
}
