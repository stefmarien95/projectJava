import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MijnSongComponent } from './mijn-song.component';

describe('MijnSongComponent', () => {
  let component: MijnSongComponent;
  let fixture: ComponentFixture<MijnSongComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MijnSongComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MijnSongComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
