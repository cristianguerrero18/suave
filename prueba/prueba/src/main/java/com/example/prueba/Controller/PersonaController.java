package com.example.prueba.Controller;




import com.example.prueba.Entity.Persona;
import com.example.prueba.Repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    private PersonaRepository personaRepository;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("personas", personaRepository.findAll());
        return "index";
    }

    @GetMapping("/nueva")
    public String nuevaPersona(Model model) {
        model.addAttribute("persona", new Persona());
        return "formpersona";
    }

    @PostMapping("/guardar")
    public String guardarPersona(@ModelAttribute Persona persona, RedirectAttributes redirectAttributes) {
        personaRepository.save(persona);
        redirectAttributes.addFlashAttribute("mensaje", "Persona guardada con éxito");
        return "redirect:/personas/";
    }

    @GetMapping("/editar/{id}")
    public String editarPersona(@PathVariable Long id, Model model) {
        model.addAttribute("persona", personaRepository.findById(id).orElse(null));
        return "formpersona";
    }

    @GetMapping("/ver/{id}")
    public String verPersona(@PathVariable Long id, Model model) {
        model.addAttribute("persona", personaRepository.findById(id).orElse(null));
        return "verpersona";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarPersona(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        personaRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensaje", "Persona eliminada con éxito");
        return "redirect:/personas/";
    }
}
