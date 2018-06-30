using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using ProjektowanieBazDanychT1.Data;
using ProjektowanieBazDanychT1.Models;

namespace ProjektowanieBazDanychT1.Controllers
{
    public class HomeController : Controller
    {
        private Random _randomizer;
        private ApplicationDbContext _dbContext;

        public HomeController(ApplicationDbContext dbContext)
        {
            _randomizer = new Random();
            _dbContext = dbContext;
        }
        public IActionResult Index()
        {
            
            
            return View(_dbContext.Tests);
        }

        public IActionResult Create()
        {
            return View(new Test());
        }

        [HttpPost]
        public IActionResult Create(Test test)
        {
            _dbContext.Tests.Add(test);
            _dbContext.SaveChanges();
            return RedirectToAction("Index");
        }
        
        [HttpGet]
        public IActionResult Wypelnij(int id)
        {   
            return View(id);
        }

        [HttpPost]
        public IActionResult Wypelnij()
        {
            var testId = Int32.Parse(HttpContext.Request.Form["testId"]);
            var test = _dbContext.Tests.Find(testId);
            test.Answers = new List<Answers>();
            var questions = _dbContext.Questions.Select(n => n);
            var listaOdpowiedzi = new List<Answers>();

            foreach(var question in questions)
            {
                var odpowiedz = HttpContext.Request.Form[question.Id.ToString()];
                var answer = new Answers() { Question = question, Answer = odpowiedz, IsCorect = odpowiedz == question.CorectAnswer ? true : false };
                test.Answers.Add(answer);
            }
            test.WhenParticipated = DateTime.Now;
            _dbContext.Tests.Update(test);
            _dbContext.Answers.AddRange(test.Answers);
            _dbContext.SaveChanges();
            return RedirectToAction("Index");
        }

        [HttpGet]
        public IActionResult Podglad(int id)
        {
            var test = _dbContext.Tests.Include(n => n.Answers).FirstOrDefault(n => n.Id == id);
            var answers = test.Answers;
            return View(answers);
        }
    }
}